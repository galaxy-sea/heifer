package plus.wcj.heifer.boot.repository.dao.rbac.role;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import plus.wcj.heifer.boot.entity.rbac.role.RbacRole;

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

    default List<RbacRole> selectByRbacTenantId(@Param("rbacTenantId") Long rbacTenantId) {
        return this.selectList(new LambdaQueryWrapper<RbacRole>().eq(RbacRole::getRbacTenantId, rbacTenantId));
    }
}
