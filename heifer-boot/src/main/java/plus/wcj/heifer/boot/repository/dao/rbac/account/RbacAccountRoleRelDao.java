package plus.wcj.heifer.boot.repository.dao.rbac.account;

import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.boot.entity.rbac.account.RbacAccountRoleRel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* <p>
 * 账户拥有角色关系表 Mapper 接口
 * </p>
*
* @author changjin wei(魏昌进)
* @since 2021-11-22
*/
 public interface RbacAccountRoleRelDao extends BaseMapper<RbacAccountRoleRel> {

   List<RbacAccountRoleRel> selectByRbacAccountId(@Param("rbacAccountId") Long rbacAccountId);
   List<RbacAccountRoleRel> selectByRbacRoleId(@Param("rbacRoleId") Long rbacRoleId);
 }
