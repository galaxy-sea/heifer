package plus.wcj.heifer.plugin.iam.service;


import plus.wcj.heifer.common.mybatisplus.IService;
import plus.wcj.heifer.plugin.iam.dto.RoleDto;
import plus.wcj.heifer.plugin.iam.entity.IamRole;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-04-23
 */
public interface IamRoleService extends IService<IamRole, Long> {

    List<IamRole> listByIamTenantId(Long iamTenantId);

    List<RoleDto> ListRoleDto(Long accountId, Long tenantId);
}
