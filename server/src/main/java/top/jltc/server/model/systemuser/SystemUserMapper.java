package top.jltc.server.model.systemuser;

import org.apache.ibatis.annotations.*;

@Mapper
public interface SystemUserMapper {

  @Select("SELECT * FROM sys_user WHERE LOGIN_NAME = #{loginName}")
  @Results(id = "SystemUserResultMap", value = {
    @Result(id = true, column = "USER_ID", property = "userId"),
    @Result(column = "LOGIN_NAME", property = "loginName"),
    @Result(column = "LOGIN_PWD", property = "loginPwd"),
    @Result(column = "USER_NAME", property = "userName"),
    @Result(column = "STATUS", property = "status"),
    @Result(column = "LAST_LOGIN_TIME", property = "lastLoginTime"),
    @Result(column = "LAST_LOGIN_IP", property = "lastLoginIp"),
    @Result(column = "CREATE_TIME", property = "createTime"),
    @Result(column = "EMAIL", property = "email"),
    @Result(column = "TEL", property = "tel"),
    @Result(column = "ROLE_ID", property = "roleId")
  })
  SystemUserEntity selectByLoginName(String loginName);

  @Select("SELECT * FROM sys_user WHERE USER_ID = #{userId}")
  @ResultMap(value = {"SystemUserResultMap"})
  SystemUserEntity selectByUserId(Integer userId);

  @Select("INSERT INTO sys_user (LOGIN_NAME, LOGIN_PWD, USER_NAME, STATUS, LAST_LOGIN_TIME, LAST_LOGIN_IP, CREATE_TIME, EMAIL, TEL, ROLE_ID) VALUES (#{loginName}, #{loginPwd}, #{userName}, #{status}, #{lastLoginTime}, #{lastLoginIp}, #{createTime}, #{email}, #{tel}, #{roleId})")
  @ResultMap(value = {"SystemUserResultMap"})
  Integer insert(SystemUserEntity systemUserEntity);

  @Select("UPDATE sys_user SET LOGIN_NAME = #{loginName}, LOGIN_PWD = #{loginPwd}, USER_NAME = #{userName}, STATUS = #{status}, LAST_LOGIN_TIME = #{lastLoginTime}, LAST_LOGIN_IP = #{lastLoginIp}, CREATE_TIME = #{createTime}, EMAIL = #{email}, TEL = #{tel}, ROLE_ID = #{roleId} WHERE USER_ID = #{userId}")
  @ResultMap(value = {"SystemUserResultMap"})
  Integer update(SystemUserEntity systemUserEntity);

  @Select("UPDATE sys_user SET STATUS = 2 WHERE USER_ID = #{userId}")
  @ResultMap(value = {"SystemUserResultMap"})
  Integer disableByUserId(Integer userId);

}
