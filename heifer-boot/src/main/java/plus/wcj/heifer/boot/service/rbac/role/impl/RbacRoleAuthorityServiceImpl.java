package plus.wcj.heifer.boot.service.rbac.role.impl;

import plus.wcj.heifer.boot.entity.rbac.role.RbacRoleAuthority;
import plus.wcj.heifer.boot.repository.dao.rbac.role.RbacRoleAuthorityDao;
import plus.wcj.heifer.boot.service.rbac.role.RbacRoleAuthorityService;
import plus.wcj.heifer.boot.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色拥有功能权限关系表 服务实现类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@Service
public class RbacRoleAuthorityServiceImpl extends ServiceImpl<RbacRoleAuthorityDao, RbacRoleAuthority, Long> implements RbacRoleAuthorityService {

}
