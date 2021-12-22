package plus.wcj.heifer.plugin.rbac.dao.account;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.plugin.rbac.entity.account.RbacAccountDataPower;

import java.util.List;

/**
* <p>
 * 账户数据权限 Mapper 接口
 * </p>
*
* @author changjinwei
* @since 2021-11-28
*/
 public interface RbacAccountDataPowerDao extends BaseMapper<RbacAccountDataPower> {

   List<RbacAccountDataPower> selectByRbacAccountId(@Param("rbacAccountId") Long rbacAccountId);
   List<RbacAccountDataPower> selectByRbacDeptId(@Param("rbacDeptId") Long rbacDeptId);
   List<RbacAccountDataPower> selectByRbacTenantId(@Param("rbacTenantId") Long rbacTenantId);
 }
