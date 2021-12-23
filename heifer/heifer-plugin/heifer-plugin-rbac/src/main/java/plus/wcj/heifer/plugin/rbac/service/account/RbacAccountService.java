package plus.wcj.heifer.plugin.rbac.service.account;


import plus.wcj.heifer.boot.mybatisplus.IService;
import plus.wcj.heifer.plugin.rbac.pojo.dto.AccountDto;
import plus.wcj.heifer.plugin.rbac.pojo.dto.RoleDto;
import plus.wcj.heifer.plugin.rbac.pojo.dto.TenantDto;
import plus.wcj.heifer.plugin.rbac.pojo.entity.account.RbacAccount;

import java.util.List;

/**
 * <p>
 * 账户表 服务类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
public interface RbacAccountService extends IService<RbacAccount, Long> {

    AccountDto getBy(String phone);

    List<String> getAllPermission(Long id, Long tenantId);

    List<RoleDto> getAllRole(Long id, Long tenantId);

    List<TenantDto> getAllTenant(String accountId);
}
