package plus.wcj.heifer.boot.service.rbac.role.impl;

import org.springframework.stereotype.Service;
import plus.wcj.heifer.boot.repository.dao.rbac.role.RbacRoleDao;
import plus.wcj.heifer.boot.entity.rbac.role.RbacRole;
import plus.wcj.heifer.boot.extension.service.impl.ServiceImpl;
import plus.wcj.heifer.boot.service.rbac.role.RbacRoleService;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@Service
public class RbacRoleServiceImpl extends ServiceImpl<RbacRoleDao, RbacRole, Long> implements RbacRoleService {

}
