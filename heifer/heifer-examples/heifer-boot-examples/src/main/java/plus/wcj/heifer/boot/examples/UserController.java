package plus.wcj.heifer.boot.examples;

import plus.wcj.heifer.metadata.bean.Result;
import plus.wck.heifer.api.User;
import plus.wck.heifer.api.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/20
 */
@RestController
@RequestMapping(value = UserService.path)
public class UserController implements UserService {
    @Override
    public User getUser(long id) {
        User user = new User();
        user.setName("xiaowei");
        user.setAge(13);

        return user;
    }

    @Override
    public User getResultv1() {
        User user = new User();
        user.setName("xiaowei");
        user.setAge(13);
        return user;
    }

    @Override
    public Result<User> getResultv2() {
        User user = new User();
        user.setName("xiaowei");
        user.setAge(13);
        return Result.success(user);
    }
}
