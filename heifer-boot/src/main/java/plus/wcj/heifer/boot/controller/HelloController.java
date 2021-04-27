package plus.wcj.heifer.boot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.common.mvc.result.ResultResponseBody;
import plus.wcj.heifer.boot.common.mvc.result.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * @author changjin wei(魏昌进)
 * @date 2021/4/23
 */
@Api(tags = "测试测试")
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
    @ApiOperation("测试测试")
    public Map<String, Object> hello() {
        return INFO;
    }

    @GetMapping("/result")
    @ResponseBody
    public Result<Map<String, Object>> helloResult() {
        return Result.success(INFO);
    }

    @GetMapping("/resultBody")
    @ResultResponseBody
    public Map<String, Object> helloResultBody() {
        return INFO;
    }

    @GetMapping(value = "testInt")
    @ResultResponseBody
    public Integer testInt() throws Exception {
        return 123;
    }

    @GetMapping(value = "testString")
    @ResultResponseBody
    public String testString() throws Exception {
        return "123";
    }
}


