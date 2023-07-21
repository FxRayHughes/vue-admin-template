package top.jltc.server.model.login;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.web.bind.annotation.*;
import top.jltc.server.model.systemuser.SystemUserEntity;
import top.jltc.server.utils.Pair;
import top.jltc.server.utils.ResponseResult;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user")
public class LoginController {


  @Resource
  private LoginService loginService;

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public ResponseResult<Pair<String, String>> login(@RequestBody JSONObject jsonObject) {
    SystemUserEntity systemUserEntity = jsonObject.toJavaObject(SystemUserEntity.class);
    return loginService.login(systemUserEntity);
  }

}
