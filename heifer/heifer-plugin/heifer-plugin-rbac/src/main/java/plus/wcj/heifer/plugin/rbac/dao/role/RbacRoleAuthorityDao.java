package plus.wcj.heifer.plugin.rbac.dao.role;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.plugin.rbac.entity.role.RbacRoleAuthority;

import java.util.List;


/**
* <p>
 * 角色拥有功能权限关系表 Mapper 接口
 * </p>
*
* @author changjinwei
* @since 2021-11-28
*/
 public interface RbacRoleAuthorityDao extends BaseMapper<RbacRoleAuthority> {

   List<RbacRoleAuthority> selectByRbacRoleId(@Param("rbacRoleId") Long rbacRoleId);
   List<RbacRoleAuthority> selectByRbacTenantId(@Param("rbacTenantId") Long rbacTenantId);
   List<RbacRoleAuthority> selectByRbacPermissionId(@Param("rbacPermissionId") Long rbacPermissionId);
 }
