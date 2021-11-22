package plus.wcj.heifer.boot.repository.dao.rbac.account;

import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.boot.entity.rbac.account.RbacAccountAuthority;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* <p>
 * 账户拥有功能权限表 Mapper 接口
 * </p>
*
* @author changjin wei(魏昌进)
* @since 2021-11-22
*/
 public interface RbacAccountAuthorityDao extends BaseMapper<RbacAccountAuthority> {

   List<RbacAccountAuthority> selectByRbacAccountId(@Param("rbacAccountId") Long rbacAccountId);
   List<RbacAccountAuthority> selectByRbacPermissionId(@Param("rbacPermissionId") Long rbacPermissionId);
 }
