package top.jltc.server.model.loginuser;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.jltc.server.model.systemuser.SystemUserEntity;
import top.jltc.server.model.systemuser.SystemUserService;

import javax.annotation.Resource;
import java.util.Objects;

@Service
public class LoginUserServiceImpl implements UserDetailsService {

  @Resource
  private SystemUserService systemUserService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    SystemUserEntity systemUserEntity = systemUserService.selectByLoginName(username);
    if (Objects.isNull(systemUserEntity)) {
      throw new RuntimeException("用户不存在");
    }
    return new LoginUser(systemUserEntity);
  }
}
