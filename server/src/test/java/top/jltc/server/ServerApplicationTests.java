package top.jltc.server;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class ServerApplicationTests {

  @Test
  void contextLoads() {

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    System.out.println(bCryptPasswordEncoder.encode("admin123456"));
    System.out.println(bCryptPasswordEncoder.matches("admin123456", "$2a$10$9ZeOECLl34wVr0gXROZKb.VxmXP3YdQrF5AxaZUJzxlq1PbV.EBUy"));


  }

}
