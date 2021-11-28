package plus.wcj.heifer.boot.repository.dao.rbac.account;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import plus.wcj.heifer.boot.entity.rbac.account.RbacAccountRoleRel;

import java.util.List;

/**
* <p>
 * 账户拥有角色关系表 Mapper 接口
 * </p>
*
* @author changjinwei
* @since 2021-11-28
*/
 public interface RbacAccountRoleRelDao extends BaseMapper<RbacAccountRoleRel> {

   List<RbacAccountRoleRel> selectByRbacAccountId(@Param("rbacAccountId") Long rbacAccountId);
   List<RbacAccountRoleRel> selectByRbacRoleId(@Param("rbacRoleId") Long rbacRoleId);
   List<RbacAccountRoleRel> selectByRbacTenantId(@Param("rbacTenantId") Long rbacTenantId);
 }
