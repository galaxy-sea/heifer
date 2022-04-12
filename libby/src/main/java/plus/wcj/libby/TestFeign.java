package plus.wcj.libby;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/7
 */
@FeignClient(value = "test", contextId = "test", url = "http://127.0.0.1:6789/hahah" )
public interface TestFeign {
    @GetMapping
    User<User> hello();
}
