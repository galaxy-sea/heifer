package plus.wcj.heifer.boot.common.security.userdetails.dao;

import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacPermissionDto;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacRoleDto;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacUserDto;

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
     * @param ids 角色id列表
     *
     * @return 权限列表
     */
    List<RbacPermissionDto> selectPermissionByRoleIdList(@Param("ids") List<Long> ids);

    /**
     * 根据用户查询权限列表
     *
     * @param userId 用户列表
     *
     * @return 权限列表
     */
    List<RbacPermissionDto> selectPermissionByUserId(@Param("userId") Long userId);

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
     * @param usernameList 用户名列表
     *
     * @return 用户列表
     */
    List<RbacUserDto> findUserByUsernameIn(@Param("usernameList") List<String> usernameList);


}
