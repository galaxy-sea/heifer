package plus.wcj.heifer.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.common.mvc.result.ResponseResultBody;
import plus.wcj.heifer.boot.common.mvc.result.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * @author changjin wei(魏昌进)
 * @date 2021/4/23
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    private static final HashMap<String, Object> INFO;

    static {
        INFO = new HashMap<>();
        INFO.put("name", "galaxy");
        INFO.put("age", "70");
    }

    @GetMapping("/hello")
    @ResponseResultBody
    public Map<String, Object> hello() {
        return INFO;
    }

    @GetMapping("/result")
    @ResponseBody
    public Result<Map<String, Object>> helloResult() {
        return Result.success(INFO);
    }

    @GetMapping("/resultBody")
    @ResponseResultBody
    public Map<String, Object> helloResultBody() {
        return INFO;
    }

    @GetMapping(value = "testInt")
    @ResponseResultBody
    public Integer testInt() throws Exception {
        return 123;
    }

    @GetMapping(value = "testString")
    @ResponseResultBody
    public String testString() throws Exception {
        return "123";
    }
}


