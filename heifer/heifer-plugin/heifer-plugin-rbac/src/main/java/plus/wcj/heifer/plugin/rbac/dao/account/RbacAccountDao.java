package plus.wcj.heifer.plugin.rbac.dao.account;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.plugin.rbac.dto.RoleDto;
import plus.wcj.heifer.plugin.rbac.entity.account.RbacAccount;

import java.util.List;


/**
 * <p>
 * 账户表 Mapper 接口
 * </p>
 *
 * @author changjinwei
 * @since 2021-11-28
 */
public interface RbacAccountDao extends BaseMapper<RbacAccount> {

    List<String> selectPermissionBy(@Param("id") Long id, @Param("tenantId") Long tenantId, @Param("roleList") List<RoleDto> roleList);

    List<RoleDto> selectRoleBy(@Param("id") Long id, @Param("tenantId") Long tenantId);
}
