package plus.wcj.heifer.plugin.rbac.dao.org;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.plugin.rbac.pojo.entity.tenant.RbacTenantAuthority;

import java.util.List;

/**
 * <p>
 * 租户拥有的权限 Mapper 接口
 * </p>
 *
 * @author changjinwei
 * @since 2021-11-28
 */
public interface RbacTenantAuthorityDao extends BaseMapper<RbacTenantAuthority> {

    List<RbacTenantAuthority> selectByRbacTenantId(@Param("rbacTenantId") Long rbacTenantId);

    List<RbacTenantAuthority> selectByRbacPermissionId(@Param("rbacPermissionId") Long rbacPermissionId);
}
