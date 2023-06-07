package plus.wcj.heifer.common.swagger.examples;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Javadoc index(Javadoc javadoc) {
        return javadoc;
    }


}
