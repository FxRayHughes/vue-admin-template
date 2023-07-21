package top.jltc.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import top.jltc.server.filter.JwtAuthenticationTokenFilter;
import top.jltc.server.utils.RedisCache;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SecurityConfig {

  @Resource
  private UserDetailsService userServiceImpl;

  @Resource
  private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

  @Resource
  private RedisCache redisCache;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
      .userDetailsService(userServiceImpl)
      .passwordEncoder(passwordEncoder())
      .and()
      .build();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(AuthenticationManager authenticationManager, HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
      .csrf().disable()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .authorizeRequests()
      .antMatchers("/api/user/login").anonymous()
      .antMatchers("/api/user/register").anonymous()
      .antMatchers("/api/user/logout").anonymous()
      .antMatchers("/api/user/refresh").anonymous()
      .anyRequest().authenticated()
      .and()
      .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
      .cors()
      .and()
      .build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedOrigins(Collections.singletonList("*"));
    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT"));

    configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
    configuration.addAllowedHeader("*");
    configuration.addAllowedMethod("*");
    configuration.setAllowCredentials(false);
    configuration.addExposedHeader("*");
    configuration.setMaxAge(3600000L);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //对所有url生效
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

}
