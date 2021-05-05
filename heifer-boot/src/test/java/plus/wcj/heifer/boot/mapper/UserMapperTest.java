package plus.wcj.heifer.boot.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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


    @Test
    public void pageTest() {
        Page<User> userPage = userMapper.selectPage(new Page<User>(1, 20), null);
        System.out.println(userPage.getRecords());
    }

    @Test
    public void xmlPageTest() {
        Page<User> userPage = userMapper.selectBy(new Page<User>(1, 20));
        System.out.println(userPage.getRecords());
    }
}