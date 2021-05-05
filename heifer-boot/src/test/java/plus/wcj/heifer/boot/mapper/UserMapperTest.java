package plus.wcj.heifer.boot.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import plus.wcj.heifer.boot.entity.User;
import plus.wcj.heifer.boot.mapper.rbac.UserMapper;

import java.util.List;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void MybatisPlusTest() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
}