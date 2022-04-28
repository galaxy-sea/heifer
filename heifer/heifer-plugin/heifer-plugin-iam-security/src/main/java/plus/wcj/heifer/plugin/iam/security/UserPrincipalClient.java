package plus.wcj.heifer.plugin.iam.security;

import plus.wcj.heifer.metadata.tenant.UserPrincipalService;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/1/13
 */
@FeignClient("rbac")
public interface UserPrincipalClient extends UserPrincipalService {
}
