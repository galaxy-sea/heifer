package plus.wcj.heifer.boot.repository.dao.rbac.account;

import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.boot.entity.rbac.account.RbacAccountManage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* <p>
 * 账户与租户的绑定关系 Mapper 接口
 * </p>
*
* @author changjin wei(魏昌进)
* @since 2021-11-22
*/
 public interface RbacAccountManageDao extends BaseMapper<RbacAccountManage> {

   List<RbacAccountManage> selectByRbacAccountId(@Param("rbacAccountId") Long rbacAccountId);
   List<RbacAccountManage> selectByRbacTenantId(@Param("rbacTenantId") Long rbacTenantId);
   List<RbacAccountManage> selectByRbacDeptId(@Param("rbacDeptId") Long rbacDeptId);
 }
