package plus.wcj.heifer.plugin.iam.service;


import plus.wcj.heifer.plugin.iam.dto.JwtDto;
import plus.wcj.heifer.plugin.iam.dto.LoginDto;
import plus.wcj.heifer.plugin.iam.dto.TenantDto;

import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/12/23
 */
public interface AuthService {
    JwtDto login(LoginDto loginDto);

    List<TenantDto> listAllTenant(String authorization);
}
