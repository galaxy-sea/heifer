package plus.wcj.heifer.plugin.rbac.dao.account;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.plugin.rbac.entity.account.RbacAccountAuthority;

import java.util.List;

/**
* <p>
 * 账户拥有功能权限表 Mapper 接口
 * </p>
*
* @author changjinwei
* @since 2021-11-28
*/
 public interface RbacAccountAuthorityDao extends BaseMapper<RbacAccountAuthority> {

   List<RbacAccountAuthority> selectByRbacAccountId(@Param("rbacAccountId") Long rbacAccountId);
   List<RbacAccountAuthority> selectByRbacTenantId(@Param("rbacTenantId") Long rbacTenantId);
   List<RbacAccountAuthority> selectByRbacPermissionId(@Param("rbacPermissionId") Long rbacPermissionId);
 }
