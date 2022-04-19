package plus.wcj.heifer.boot.examples;

import plus.wcj.heifer.metadata.annotation.ResultResponseBody;
import plus.wcj.heifer.metadata.exception.ResultException;
import plus.wcj.heifer.metadata.exception.ResultStatusEnum;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/17
 */
@RestController
@RequestMapping
@ResultResponseBody
public class ResultController {

    @GetMapping("hello")
    public Map<String, String> hello() {
        return new LinkedHashMap<String, String>() {{
            put("hello", "hello");
            put("world", "world");
        }};
    }

    @GetMapping("error401")
    public Map<String, String> error401() {
        throw new ResultException(ResultStatusEnum.UNAUTHORIZED);
    }


    @GetMapping(value = "string", produces = MediaType.APPLICATION_JSON_VALUE)
    public String string() {
        return "hello, world";
    }

    @GetMapping(value = "validator")
    public String validator(@Validated ValidatedDto validatedDto) {
        return "hello, world";
    }
}
