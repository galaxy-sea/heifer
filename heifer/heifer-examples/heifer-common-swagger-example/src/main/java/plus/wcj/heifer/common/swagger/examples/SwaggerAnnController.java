package plus.wcj.heifer.common.swagger.examples;

import io.swagger.v3.oas.annotations.tags.Tag;
import plus.wcj.heifer.metadata.annotation.IgnoreWebSecurity;
import plus.wcj.heifer.metadata.annotation.ResponseBodyResult;
import plus.wcj.heifer.metadata.iam.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/6/21
 */
@RestController
@RequestMapping("security")
@Tag(name = "data")
public class SwaggerAnnController {

    @GetMapping
    public String index() {
        return "123123";
    }

    @GetMapping("sign")
    @ResponseBodyResult
    public User test1(User user) {
        return user;
    }


    @GetMapping("permission")
    @ResponseBodyResult
//    @PreAuthorize("hasAuthority('heifer:admin:test')")
    public User test2(User user) {
        return user;
    }

    @GetMapping("role")
    @ResponseBodyResult
//    @PreAuthorize("hasRole('admin')")
    public User testRole(User user) {
        return user;
    }

    @GetMapping("not")
    @ResponseBodyResult
//    @PreAuthorize("hasAuthority('admin123123')")
    public User testUser(User user) {
        return user;
    }

    @RequestMapping(value = {"ignoreWebSecurity", "wodesijie"})
    @ResponseBodyResult
    @IgnoreWebSecurity
    public String ignoreWebSecurity() {
        return "IgnoreWebSecurity";
    }


}
