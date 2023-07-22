package top.jltc.server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import top.jltc.server.model.assembly.AssemblyEntity;
import top.jltc.server.model.assembly.AssemblyMapper;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class ServerApplicationTests {
  @Resource
  AssemblyMapper assemblyMapper;

  @Test
  public void mapperTest() throws ParseException {
    AssemblyEntity assemblyEntity = new AssemblyEntity();
    assemblyEntity.setStart_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-07-24 00:20:00"));
    assemblyEntity.setEnd_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-07-27 00:20:00"));
    List<AssemblyEntity> like = assemblyMapper.like(assemblyEntity);
    System.err.println(like);
  }

  @Test
  public void Test() {
    System.out.println(new Date("new Date(2023-07-25 00:00:00)"));
  }

  @Test
  void contextLoads() {

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    System.out.println(bCryptPasswordEncoder.encode("admin123456"));
    System.out.println(bCryptPasswordEncoder.matches("admin123456", "$2a$10$9ZeOECLl34wVr0gXROZKb.VxmXP3YdQrF5AxaZUJzxlq1PbV.EBUy"));


  }

}
