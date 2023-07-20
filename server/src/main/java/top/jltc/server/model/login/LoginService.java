package top.jltc.server.model.login;

import top.jltc.server.model.systemuser.SystemUserEntity;
import top.jltc.server.utils.Pair;
import top.jltc.server.utils.ResponseResult;

public interface LoginService {
  ResponseResult<Pair<String, String>> login(SystemUserEntity systemUserEntity);
}
