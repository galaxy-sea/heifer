package plus.wcj.heifer.plugin.rbac.security;

import plus.wcj.heifer.plugin.rbac.pojo.dto.JwtDto;
import plus.wcj.heifer.plugin.rbac.pojo.dto.LoginDto;
import plus.wcj.heifer.plugin.rbac.pojo.dto.TenantDto;

import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/12/23
 */
public interface AuthService {
    JwtDto login(LoginDto loginDto);

    List<TenantDto> getAllTenant(String authorization);
}
