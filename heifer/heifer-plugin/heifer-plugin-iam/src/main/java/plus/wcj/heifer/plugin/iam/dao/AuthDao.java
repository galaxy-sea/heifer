package plus.wcj.heifer.plugin.iam.dao;

import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.plugin.iam.dto.AccountDto;
import plus.wcj.heifer.plugin.iam.dto.RoleDto;
import plus.wcj.heifer.plugin.iam.dto.TenantDto;

import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/04/28
 */

public interface AuthDao {
    AccountDto selectAccountByPhone(@Param("phone") String phone);

    List<TenantDto> selectAllTenant(@Param("accountId") String accountId);

    List<RoleDto> listRole(@Param("accountId") Long accountId, @Param("tenantId") Long tenantId);

    List<String> listPermission(@Param("accountId") Long accountId, @Param("tenantId") Long tenantId, @Param("roleList") List<RoleDto> roleList);

    List<Long> listPower(@Param("accountId") Long accountId, @Param("tenantId") Long tenantId);
}
