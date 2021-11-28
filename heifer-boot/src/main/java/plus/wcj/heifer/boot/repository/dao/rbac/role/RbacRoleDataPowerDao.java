package plus.wcj.heifer.boot.repository.dao.rbac.role;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import plus.wcj.heifer.boot.entity.rbac.role.RbacRoleDataPower;

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
