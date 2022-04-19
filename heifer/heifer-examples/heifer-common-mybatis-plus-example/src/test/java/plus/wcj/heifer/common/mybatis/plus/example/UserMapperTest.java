package plus.wcj.heifer.common.mybatis.plus.example;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/19
 */
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectGradeEnumTest() {
        List<User> users = userMapper.selectList(null);
        Assert.isTrue(!CollectionUtils.isEmpty(users));
        for (User user : users) {
            GradeEnum grade = user.getGrade();
            Assert.isTrue(grade != null);
        }
    }


    @Test
    public void insterGradeEnumTest() {
        User user = new User();
        user.setId(0L);
        user.setName("xiaowei");
        user.setAge(0);
        user.setEmail("a@qq.com");
        user.setGrade(GradeEnum.SECONDORY);
        userMapper.insert(user);

        user = userMapper.selectEnum(user);
        Assert.isTrue(user != null);
        Assert.isTrue(user.getGrade() == GradeEnum.SECONDORY);
        user = userMapper.selectTest();
        Assert.isTrue(user != null);
        Assert.isTrue(user.getGrade() == GradeEnum.SECONDORY);

    }


}