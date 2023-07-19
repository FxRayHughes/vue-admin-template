package top.jltc.server.model.systemuser;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/system_user")
public class SystemUserController {

  @Resource
  private SystemUserService systemUserService;

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public SystemUserEntity loginByName(String loginName) {
    return systemUserService.selectByLoginName(loginName);
  }

}
