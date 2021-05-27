package plus.wcj.heifer.boot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class PasswordTest {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void test() {
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);
        System.out.println(bCryptPasswordEncoder.matches("123456", encode));
    }
}
