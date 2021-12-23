package plus.wcj.heifer.plugin.rbac.dao.role;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.plugin.rbac.pojo.entity.role.RbacRoleDataPower;

import java.util.List;

/**
* <p>
 * 角色数据权限 Mapper 接口
 * </p>
*
* @author changjinwei
* @since 2021-11-28
*/
 public interface RbacRoleDataPowerDao extends BaseMapper<RbacRoleDataPower> {

   List<RbacRoleDataPower> selectByRbacRoleId(@Param("rbacRoleId") Long rbacRoleId);
   List<RbacRoleDataPower> selectByRbacDeptId(@Param("rbacDeptId") Long rbacDeptId);
   List<RbacRoleDataPower> selectByRbacTenantId(@Param("rbacTenantId") Long rbacTenantId);
 }
