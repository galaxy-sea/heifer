package plus.wcj.libby.controller;

import plus.wcj.libby.User;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/11
 */
@Configuration
public class TestConfig {

    @Bean
    public List<User> userList() {
        return new ArrayList<User>() {{
            add(new User("11", "222", null));
            add(new User("11", "222", null));
        }};
    }

    @Bean
    public User users() {
        return (new User("11", "222", null));
    }

}
