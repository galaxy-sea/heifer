package plus.wcj.heifer.boot.repository.dao.rbac.role;

import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.boot.entity.rbac.role.RbacRoleAuthority;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* <p>
 * 角色拥有功能权限关系表 Mapper 接口
 * </p>
*
* @author changjin wei(魏昌进)
* @since 2021-11-22
*/
 public interface RbacRoleAuthorityDao extends BaseMapper<RbacRoleAuthority> {

   List<RbacRoleAuthority> selectByRbacRoleId(@Param("rbacRoleId") Long rbacRoleId);
   List<RbacRoleAuthority> selectByRbacPermissionId(@Param("rbacPermissionId") Long rbacPermissionId);
 }
