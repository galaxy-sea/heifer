package wcj.plus.heifer.common.feign.example;

import org.junit.jupiter.api.Test;
import plus.wcj.heifer.metadata.bean.Result;
import plus.wck.heifer.api.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/20
 */
@SpringBootTest
public class UserClientTest {

    @Autowired
    private UserClient userClient;


    @Test
    public void getUser() {
        User getUser = userClient.getUser(1);

        User user = new User();
        user.setName("xiaowei");
        user.setAge(13);

        Assert.isTrue(user.equals(getUser));

    }


    @Test
    public void getResultV1() {
        User getResult = userClient.getResultv1();

        User user = new User();
        user.setName("xiaowei");
        user.setAge(13);

        Assert.isTrue(user.equals(getResult));
    }


    @Test
    public void getResultV2() {
        Result<User> resultv2 = userClient.getResultv2();

        Assert.isTrue(resultv2 != null);

        User user = new User();
        user.setName("xiaowei");
        user.setAge(13);

        Assert.isTrue(user.equals(resultv2.getData()));
    }

}