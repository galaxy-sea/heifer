package plus.wcj.libby.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/2/22
 */
@RestController
@RequestMapping("hello")
public class Haha {
    @GetMapping
    public String hahah(){
        return "hello";
    }
}
