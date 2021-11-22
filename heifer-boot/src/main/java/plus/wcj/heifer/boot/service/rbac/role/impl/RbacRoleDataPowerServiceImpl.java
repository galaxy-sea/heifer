package plus.wcj.heifer.boot.service.rbac.role.impl;

import plus.wcj.heifer.boot.entity.rbac.role.RbacRoleDataPower;
import plus.wcj.heifer.boot.repository.dao.rbac.role.RbacRoleDataPowerDao;
import plus.wcj.heifer.boot.service.rbac.role.RbacRoleDataPowerService;
import plus.wcj.heifer.boot.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色数据权限 服务实现类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@Service
public class RbacRoleDataPowerServiceImpl extends ServiceImpl<RbacRoleDataPowerDao, RbacRoleDataPower, Long> implements RbacRoleDataPowerService {

}
