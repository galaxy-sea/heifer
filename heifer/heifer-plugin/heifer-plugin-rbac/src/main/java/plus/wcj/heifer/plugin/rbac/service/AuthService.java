package plus.wcj.heifer.plugin.rbac.service;

import plus.wcj.heifer.plugin.rbac.pojo.dto.AccountDto;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/12/23
 */
public interface AuthService {
    AccountDto login(String phone, String password);
}
