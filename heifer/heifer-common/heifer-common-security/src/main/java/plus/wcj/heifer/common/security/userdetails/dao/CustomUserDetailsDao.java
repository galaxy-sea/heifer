package plus.wcj.heifer.common.security.userdetails.dao;

import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacAccountDto;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacAccountManageDto;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacRoleDto;

import java.util.List;
import java.util.Optional;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/5/21
 */
public interface CustomUserDetailsDao {

    /**
     * 根据用户名、邮箱、手机号查询用户
     *
     * @param username 用户名
     * @param email 邮箱
     * @param phone 手机号
     *
     * @return 用户信息
     */
    Optional<RbacAccountDto> findAccountByUsernameOrEmailOrPhone(@Param("username") String username, @Param("email") String email, @Param("phone") String phone);


    Optional<RbacAccountManageDto> findAccountManageBy(@Param("rbacAccountId") Long rbacAccountId);

    List<RbacRoleDto> selectRoleBy(@Param("rbacAccountId") Long rbacAccountId, @Param("rbacTenantId") Long rbacTenantId);

    List<String> selectDistinctPermissionBy(@Param("rbacAccountId") Long rbacAccountId, @Param("roleList") List<RbacRoleDto> roleList);

    List<Long> selectDistinctPowerBy(@Param("rbacAccountId") Long rbacAccountId);
}
