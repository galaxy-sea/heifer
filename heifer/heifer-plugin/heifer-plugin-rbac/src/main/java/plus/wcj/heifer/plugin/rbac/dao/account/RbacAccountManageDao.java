package plus.wcj.heifer.plugin.rbac.dao.account;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.plugin.rbac.entity.account.RbacAccountManage;

import java.util.List;

/**
 * <p>
 * 账户与租户的绑定关系 Mapper 接口
 * </p>
 *
 * @author changjinwei
 * @since 2021-11-28
 */
public interface RbacAccountManageDao extends BaseMapper<RbacAccountManage> {

    List<RbacAccountManage> selectByRbacAccountId(@Param("rbacAccountId") Long rbacAccountId);

    List<RbacAccountManage> selectByRbacTenantId(@Param("rbacTenantId") Long rbacTenantId);

    List<RbacAccountManage> selectByRbacDeptId(@Param("rbacDeptId") Long rbacDeptId);
}
