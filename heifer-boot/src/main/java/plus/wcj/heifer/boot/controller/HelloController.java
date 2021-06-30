package plus.wcj.heifer.boot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import plus.wcj.heifer.boot.common.mvc.result.ResultResponseBody;

/**
 * @author changjin wei(魏昌进)
 * @date 2021/4/23
 */
@Controller
@RequestMapping("/hello")
@RequiredArgsConstructor
@SuppressWarnings("all")
public class HelloController {


    @GetMapping(params = "id")
    @ResultResponseBody
    public Object get(Long id) {
        return "aaaaaa";
    }

    @GetMapping
    @ResultResponseBody
    public Object get() {
        return "bbbb";
    }

    @PostMapping
    @ResultResponseBody
    public Object post(@RequestBody Object o) {
        return null;
    }

    @PutMapping
    @ResultResponseBody
    public Object put(@RequestBody Object o) {
        return null;
    }


    @DeleteMapping
    @ResultResponseBody
    public Object delete(Long id) {
        return null;
    }


    // @GetMapping(headers = "version=1")
    // @ResponseBody
    // public String hello() {
    //     return "hello";
    // }
    //
    // @GetMapping(headers = "version=2")
    // @ResponseBody
    // public int helloInt() {
    //     return 123;
    // }
    //
    // @GetMapping(headers = "version=3")
    // @ResponseBody
    // public Result<String> result() {
    //     return Result.success("hello");
    // }

}


