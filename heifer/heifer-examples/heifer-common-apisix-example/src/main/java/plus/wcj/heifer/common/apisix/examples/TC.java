package plus.wcj.heifer.common.apisix.examples;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/5/4
 */
@RequestMapping
@RestController
public class TC {

    @GetMapping("hello")
    public Map<String, String> map() {
        return new LinkedHashMap<String, String>() {{
            put("hello", "world123");
        }};
    }


    @GetMapping("heifer-common-apisix-example/hello")
    public Map<String, String> map1() {
        return new LinkedHashMap<String, String>() {{
            put("hello", "world");
        }};
    }
}
