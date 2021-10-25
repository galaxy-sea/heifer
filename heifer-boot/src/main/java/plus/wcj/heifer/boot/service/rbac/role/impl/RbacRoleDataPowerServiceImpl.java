package plus.wcj.heifer.boot.service.rbac.role.impl;

import org.springframework.stereotype.Service;
import plus.wcj.heifer.boot.repository.dao.rbac.role.RbacRoleDataPowerDao;
import plus.wcj.heifer.boot.entity.rbac.role.RbacRoleDataPower;
import plus.wcj.heifer.boot.extension.service.impl.ServiceImpl;
import plus.wcj.heifer.boot.service.rbac.role.RbacRoleDataPowerService;

/**
 * <p>
 * 角色数据权限 服务实现类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@Service
public class RbacRoleDataPowerServiceImpl extends ServiceImpl<RbacRoleDataPowerDao, RbacRoleDataPower, Long> implements RbacRoleDataPowerService {

}
