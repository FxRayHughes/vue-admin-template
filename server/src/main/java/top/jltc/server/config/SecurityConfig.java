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

import javax.annotation.Resource;

@Configuration
public class SecurityConfig {

  @Resource
  private UserDetailsService userServiceImpl;

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
      .cors()
      .and()
      .build();
  }
}
