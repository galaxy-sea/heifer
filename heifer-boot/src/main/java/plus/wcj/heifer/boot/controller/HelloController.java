package plus.wcj.heifer.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import plus.wcj.heifer.boot.common.mvc.result.Result;

import java.util.HashMap;

/**
 * @author changjin wei(魏昌进)
 * @date 2021/4/23
 */
@Controller
@RequestMapping("/hello")
public class HelloController {


    @GetMapping(headers = "version=1")
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @GetMapping(headers = "version=2")
    @ResponseBody
    public int helloInt() {
        return 123;
    }

    @GetMapping(headers = "version=3")
    @ResponseBody
    public Result<String> result() {
        return Result.success("hello");
    }

}


