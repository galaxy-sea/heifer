package plus.wcj.heifer.boot.common.security.userdetails.dao;

import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.boot.common.mvc.resolver.tenant.Tenant;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacAdminDto;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacCustomerDto;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacPermissionDto;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacRoleDto;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacUserDto;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacUserManageDto;

import java.util.List;
import java.util.Optional;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/5/21
 */
public interface CustomUserDetailsDao {
    /**
     * 根据角色列表查询权限列表
     *
     * @param roleIds 角色id列表
     *
     * @return 权限列表
     */
    List<RbacPermissionDto> selectPermissionByRoleIdList(@Param("roleIds") List<Long> roleIds);

    /**
     * 根据用户查询权限列表
     *
     * @param userId 用户列表
     *
     * @return 权限列表
     */
    List<RbacPermissionDto> selectPermissionByUserId(@Param("userId") Long userId);

    /**
     * 根据租户查询权限列表
     *
     * @param orgId 租户id
     *
     * @return 权限列表
     */
    List<RbacPermissionDto> selectPermissionByrOrgId(@Param("orgId") Long orgId);

    /**
     * 根据用户id 查询角色列表
     *
     * @param userId 用户id
     *
     * @return 角色列表
     */
    List<RbacRoleDto> selectRoleByUserId(@Param("userId") Long userId);

    /**
     * 根据用户名、邮箱、手机号查询用户
     *
     * @param username 用户名
     * @param email 邮箱
     * @param phone 手机号
     *
     * @return 用户信息
     */
    Optional<RbacUserDto> findUserByUsernameOrEmailOrPhone(@Param("username") String username, @Param("email") String email, @Param("phone") String phone);

    /**
     * 根据用户名列表查询用户列表
     *
     * @param roleIds 角色
     * @param userId 用户
     *
     * @return 可查看的部门Id
     */
    List<Long> selectDataPower(@Param("roleIds") List<Long> roleIds, @Param("userId") Long userId);

    /**
     * 根据租户查找数据权限
     *
     * @param orgId 租户id
     *
     * @return 可查看的部门Id
     */
    List<Long> selectDataPowerByOrgId(@Param("orgId") Long orgId);


    /**
     * 获取当前用户 admin信息
     *
     * @param userId 用户id
     *
     * @return admin用户信息
     */
    RbacAdminDto findAdmin(@Param("userId") Long userId);

    /**
     * 获取当前用户 Customer信息
     *
     * @param userId 用户id
     *
     * @return Customer信息
     */
    RbacCustomerDto findCustomer(@Param("userId") Long userId);

    /**
     * 获取用户 UserManage信息
     *
     * @param userId 用户id
     *
     * @return UserManage信息
     */
    RbacUserManageDto findUserManage(@Param("userId") Long userId);

    // TODO: 2021/7/6 changjin wei(魏昌进)
    RbacUserManageDto findUserManageTest(@Param("tenant") Tenant tenant);
}
