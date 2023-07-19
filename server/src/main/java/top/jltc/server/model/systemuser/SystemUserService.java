package top.jltc.server.model.systemuser;

public interface SystemUserService {
  SystemUserEntity selectByLoginName(String loginName);

  SystemUserEntity selectByUserId(Integer userId);

  Integer insert(SystemUserEntity systemUserEntity);

  Integer update(SystemUserEntity systemUserEntity);

  Integer disableByUserId(Integer userId);

}
