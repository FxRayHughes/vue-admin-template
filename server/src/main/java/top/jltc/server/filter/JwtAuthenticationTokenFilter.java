package top.jltc.server.filter;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import top.jltc.server.model.systemuser.SystemUserEntity;
import top.jltc.server.utils.JwtUtil;
import top.jltc.server.utils.RedisCache;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

  @Resource
  private RedisCache redisCache;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    //获取token
    String token = request.getHeader("Token");
    if (!StringUtils.hasText(token)) {
      //放行
      filterChain.doFilter(request, response);
      return;
    }
    //解析token
    System.out.println("1");
    String userid;
    try {
      Claims claims = JwtUtil.parseJWT(token);
      userid = claims.getSubject();
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException("token非法");
    }
    System.out.println("2");
    //从redis中获取用户信息
    String redisKey = "login:" + userid;

    System.out.println("3");
    WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
    if (webApplicationContext != null) {
      SystemUserEntity loginUser = webApplicationContext.getBean(RedisCache.class).getCacheObject(redisKey);
      if (Objects.isNull(loginUser)) {
        throw new RuntimeException("用户未登录");
      }
      //存入SecurityContextHolder
      UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(loginUser, null, null);
      SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      //放行
      filterChain.doFilter(request, response);
    }
  }

}
