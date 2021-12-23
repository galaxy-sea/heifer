package plus.wcj.heifer.plugin.rbac.dao.account;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.plugin.rbac.pojo.entity.account.RbacAccountRoleRel;

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
