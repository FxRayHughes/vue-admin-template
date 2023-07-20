package top.jltc.server.model.login;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import top.jltc.server.model.loginuser.LoginUser;
import top.jltc.server.model.systemuser.SystemUserEntity;
import top.jltc.server.model.systemuser.SystemUserService;
import top.jltc.server.utils.JwtUtil;
import top.jltc.server.utils.Pair;
import top.jltc.server.utils.RedisCache;
import top.jltc.server.utils.ResponseResult;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

  @Resource
  private AuthenticationManager authenticationManager;

  @Resource
  private SystemUserService systemUserService;

  @Resource
  private RedisCache redisCache;

  @Override
  public ResponseResult<Pair<String, String>> login(SystemUserEntity systemUserEntity) {
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(systemUserEntity.getLoginName(), systemUserEntity.getLoginPwd());
    Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    if (Objects.isNull(authenticate)) {
      return ResponseResult.error(new Pair<>("token", "null"));
    }
    SystemUserEntity principal = ((LoginUser) authenticate.getPrincipal()).getSystemUserEntity();
    String id = principal.getUserId().toString();
    String jwt = JwtUtil.createJWT(id);

    redisCache.setCacheObject("login:" + id, systemUserService.selectByUserId(principal.getUserId()));
    return ResponseResult.success(new Pair<>("token", jwt), "登录成功");
  }

}
