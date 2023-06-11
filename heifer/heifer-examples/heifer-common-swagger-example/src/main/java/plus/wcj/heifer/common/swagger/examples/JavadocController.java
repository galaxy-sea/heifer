package plus.wcj.heifer.common.swagger.examples;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.metadata.iam.User;

import java.util.List;

/**
 * 测试JavaController
 * @author changjin wei(魏昌进)
 * @since 2022/6/21
 */
@RestController
@RequestMapping("javadoc")
@Tag(name = "javadoc")
public class JavadocController {

    /**
     * 测试Java方法
     * @param javadoc java实体类形参
     * @return Java实体类返回
     */
    @GetMapping
    public void index(Javadoc javadoc) {
    }

    @GetMapping("page")
    public Page<Javadoc> index(Page<Javadoc> javadocPage) {
        return javadocPage;
    }

    @GetMapping("user")
    public User user(User user) {
        return user;
    }

}
