package plus.wcj.heifer.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author changjin wei(魏昌进)
 * @date 2021/4/27
 */
@Controller
@ApiIgnore
public class SwaggerController {
    @GetMapping("/")
    public String index() {
        return "redirect:swagger-ui/";
    }
}
