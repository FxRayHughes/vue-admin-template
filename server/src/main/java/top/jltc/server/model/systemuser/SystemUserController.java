package top.jltc.server.model.systemuser;

import io.jsonwebtoken.Claims;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import top.jltc.server.utils.JwtUtil;
import top.jltc.server.utils.ResponseResult;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/system_user")
public class SystemUserController {

  @Resource
  private SystemUserService systemUserService;

  @RequestMapping(value = "/info/{token}", method = RequestMethod.GET)
  public ResponseResult<SystemUserEntity> getSystemUser(@PathVariable String token) throws Exception {
    // token
    Claims claims = JwtUtil.parseJWT(token); // 1
    Integer id = Integer.parseInt(claims.getSubject());
    return ResponseResult.success(systemUserService.selectByUserId(id), "获取成功");
  }

  @RequestMapping(value = "/info", method = RequestMethod.GET)
  public ResponseResult<SystemUserEntity> getSystemUserToken(@RequestHeader HttpHeaders headers) throws Exception {
    // token
    String token = headers.getFirst("token");
    Claims claims = JwtUtil.parseJWT(token); //" 1 "
    Integer id = Integer.parseInt(claims.getSubject());
    return ResponseResult.success(systemUserService.selectByUserId(id), "获取成功");
  }

}
