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

    /**
     * 获取用户名和密码
     *
     * @param phone 手机号
     *
     * @return 账户
     */
    AccountDto selectAccountByPhone(@Param("phone") String phone);

    /**
     * 获取当前accountId的全部租户信息
     *
     * @param accountId 账户信息
     *
     * @return 租户列表
     */
    List<TenantDto> selectAllTenant(@Param("accountId") String accountId);

    /**
     * 获取账户在租户中的全部角色
     *
     * @param accountId 账户id
     * @param tenantId 租户id
     *
     * @return 角色列表
     */
    List<RoleDto> listRole(@Param("accountId") Long accountId, @Param("tenantId") Long tenantId);

    /**
     * 获取账户在租户中的全部功能权限
     *
     * @param accountId 账户id
     * @param tenantId 租户id
     * @param roleList 角色列表 #{@link  plus.wcj.heifer.plugin.iam.dao.AuthDao#listRole(java.lang.Long, java.lang.Long)}
     *
     * @return 功能权限表达式列表
     */
    List<String> listPermission(@Param("accountId") Long accountId, @Param("tenantId") Long tenantId, @Param("roleList") List<RoleDto> roleList);

    /**
     * 获取账户ID在租户中的全部数据权限
     *
     * @param accountId 账户id
     * @param tenantId 租户id
     *
     * @return dept id列表
     */
    List<Long> listPower(@Param("accountId") Long accountId, @Param("tenantId") Long tenantId);

    /**
     * 获取当前账户在租户中的部门
     *
     * @param accountId 账户id
     * @param tenantId 租户id
     *
     * @return 部门id
     */
    Long getDept(@Param("accountId") Long accountId, @Param("tenantId") Long tenantId);
}
