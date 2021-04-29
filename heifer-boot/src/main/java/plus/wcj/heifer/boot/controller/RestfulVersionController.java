package plus.wcj.heifer.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.common.mvc.restful.ApiVersion;
import plus.wcj.heifer.boot.common.mvc.result.ResultResponseBody;

/**
 * @author changjin wei(魏昌进)
 * @date 2021/4/26
 */
@RestController
@ResultResponseBody
@RequestMapping("/restful")
public class RestfulVersionController {

    @GetMapping(value = "/hello")
    @ApiVersion(1)
    public String hello1() {
        return "hello Restful version1";
    }

    @ApiVersion(2)
    @GetMapping(value = "/hello")
    public String hello2() {
        return "hello Restful version2";
    }


    @GetMapping(value = "/hello",headers = "version=v1")
    public String helloV1() {
        return "hello Restful v1";
    }

    @GetMapping(value = "/hello",headers = "version=v2")
    public String helloV2() {
        return "hello Restful v2";
    }


}


