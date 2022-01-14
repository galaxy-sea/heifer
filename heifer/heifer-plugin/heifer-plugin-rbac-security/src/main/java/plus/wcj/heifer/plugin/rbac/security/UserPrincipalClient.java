package plus.wcj.heifer.plugin.rbac.security;

import plus.wcj.heifer.matedata.service.UserPrincipalService;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/1/13
 */
@FeignClient("rbac")
public interface UserPrincipalClient extends UserPrincipalService {
}
