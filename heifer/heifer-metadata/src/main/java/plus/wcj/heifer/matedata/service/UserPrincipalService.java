package plus.wcj.heifer.matedata.service;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @see <a href="https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/#spring-cloud-feign-inheritance">feign 继承支持<a/>
 * @since 2022-01-13
 */
public interface UserPrincipalService {
    @GetMapping("/account/getAllPermission")
    @Cacheable(cacheNames = "permission", key = "#p0+':'+#p1")
    List<String> getAllPermission(@RequestParam Long accountId, @RequestParam Long tenantId);

    @GetMapping("/account/getAllPower")
    @Cacheable(cacheNames = "power", key = "#p0+':'+#p1")
    List<?> getAllPower(@RequestParam Long accountId, @RequestParam Long tenantId);
}
