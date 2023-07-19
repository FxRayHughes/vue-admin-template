package top.jltc.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import top.jltc.server.model.systemuser.SystemUserEntity;
import top.jltc.server.model.systemuser.SystemUserMapper;
import top.jltc.server.model.systemuser.SystemUserService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/test")
public class TestHelloController {


  @Resource
  private SystemUserService systemUserService;

  @RequestMapping(value = "/hello", method = RequestMethod.GET)
  public SystemUserEntity hello() {
    return systemUserService.selectByLoginName("admin");
  }

}
