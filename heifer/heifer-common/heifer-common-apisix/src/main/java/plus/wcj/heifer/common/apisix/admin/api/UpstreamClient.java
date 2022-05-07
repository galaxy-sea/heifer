package plus.wcj.heifer.common.apisix.admin.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

/**
 * <a href="https://apisix.apache.org/zh/docs/apisix/admin-api#upstream">https://apisix.apache.org/zh/docs/apisix/admin-api#upstream</a>
 *
 * @author changjin wei(魏昌进)
 * @since 2022/5/4
 */
@FeignClient(name = "upstreamClient", url = "${heifer.apisix.server-addr?heifer.apisix.server-addr:http://192.168.31.112:9080}", path = "${heifer.apisix.server-path?heifer.apisix.server-path:apisix/admin/}")
public interface UpstreamClient {

    /**
     * 获取资源
     *
     * @param id upstream id
     * @param token X-API-KEY
     *
     * @return json string
     */
    @GetMapping("upstreams/{id}")
    ResponseEntity<String> upstream(@PathVariable("id") String id, @RequestHeader(Constants.TOKEN_KEY) String token);


    /**
     * 根据 id 创建资源
     *
     * @param id upstream id
     * @param body upstream info
     * @param token X-API-KEY
     *
     * @return json string
     */
    @PutMapping("upstreams/{id}")
    ResponseEntity<String> upstream(@PathVariable("id") String id, @RequestBody Map<String, Object> body, @RequestHeader(Constants.TOKEN_KEY) String token);

}
