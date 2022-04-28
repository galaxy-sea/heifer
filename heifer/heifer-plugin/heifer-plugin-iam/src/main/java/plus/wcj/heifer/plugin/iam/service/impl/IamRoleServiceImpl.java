package plus.wcj.heifer.plugin.iam.service.impl;


import plus.wcj.heifer.common.mybatisplus.impl.ServiceImpl;
import plus.wcj.heifer.plugin.iam.dao.IamRoleDao;
import plus.wcj.heifer.plugin.iam.dto.RoleDto;
import plus.wcj.heifer.plugin.iam.entity.IamRole;
import plus.wcj.heifer.plugin.iam.service.IamRoleService;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-04-23
 */
@Service
public class IamRoleServiceImpl extends ServiceImpl<IamRoleDao, IamRole, Long> implements IamRoleService {

    @Override
    public List<IamRole> listByIamTenantId(Long iamTenantId) {
        return super.getBaseMapper().selectByIamTenantId(iamTenantId);
    }

    @Override
    public List<RoleDto> ListRoleDto(Long accountId, Long tenantId) {
        return super.getBaseMapper().selectRoleDtoBy(accountId, tenantId);
    }

}
