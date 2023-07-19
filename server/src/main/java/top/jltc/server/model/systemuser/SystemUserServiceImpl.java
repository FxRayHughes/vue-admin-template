package top.jltc.server.model.systemuser;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SystemUserServiceImpl implements SystemUserService {

  @Resource
  private SystemUserMapper systemUserMapper;


  @Override
  public SystemUserEntity selectByLoginName(String loginName) {
    return systemUserMapper.selectByLoginName(loginName);
  }

  @Override
  public SystemUserEntity selectByUserId(Integer userId) {
    return systemUserMapper.selectByUserId(userId);
  }

  @Override
  public Integer insert(SystemUserEntity systemUserEntity) {
    return systemUserMapper.insert(systemUserEntity);
  }

  @Override
  public Integer update(SystemUserEntity systemUserEntity) {
    return systemUserMapper.update(systemUserEntity);
  }

  @Override
  public Integer disableByUserId(Integer userId) {
    return systemUserMapper.disableByUserId(userId);
  }
}
