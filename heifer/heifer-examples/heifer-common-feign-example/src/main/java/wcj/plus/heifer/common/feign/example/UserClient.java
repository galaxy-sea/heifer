package wcj.plus.heifer.common.feign.example;

import plus.wck.heifer.api.UserService;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/20
 */
@FeignClient(value = "heifer-boot-examples", path = UserService.path)
public interface UserClient extends UserService {





}
