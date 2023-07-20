package top.jltc.server.model.systemuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemUserEntity implements Serializable {

  private Integer UserId;

  private String LoginName;

  private String LoginPwd;

  private String UserName;

  private Integer Status;

  private Date LastLoginTime;

  private String LastLoginIp;

  private Date CreateTime;

  private String Email;

  private String Tel;

  private Integer RoleId;

  private static final long serialVersionUID = 1L;

}
