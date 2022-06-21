package plus.wcj.heifer.common.swagger.examples;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/6/21
 */
@Controller
@RequestMapping("/doc")
public class SwaggerController {

    @GetMapping
    public String index() {
        return "redirect:swagger-ui/";
    }


}
