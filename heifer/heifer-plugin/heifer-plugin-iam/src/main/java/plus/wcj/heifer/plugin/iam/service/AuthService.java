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
    /**
     * 登陆，
     * @param loginDto 登陆信息
     * @return jwt
     */
    JwtDto login(LoginDto loginDto);

    /**
     * 获取当前账户下的租户信息
     * @param authorization jwt
     * @return 租户信息列表
     */
    List<TenantDto> listAllTenant(String authorization);
}
