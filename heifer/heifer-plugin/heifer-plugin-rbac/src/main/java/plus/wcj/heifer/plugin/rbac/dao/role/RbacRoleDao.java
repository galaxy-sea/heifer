package plus.wcj.heifer.plugin.rbac.dao.role;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.plugin.rbac.pojo.entity.role.RbacRole;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author changjinwei
 * @since 2021-11-28
 */
public interface RbacRoleDao extends BaseMapper<RbacRole> {

    List<RbacRole> selectByRbacTenantId(@Param("rbacTenantId") Long rbacTenantId);
}
