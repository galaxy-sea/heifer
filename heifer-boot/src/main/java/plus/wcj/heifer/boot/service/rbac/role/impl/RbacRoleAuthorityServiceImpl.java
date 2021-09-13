package plus.wcj.heifer.boot.service.rbac.role.impl;

import org.springframework.stereotype.Service;
import plus.wcj.heifer.boot.repository.dao.rbac.role.RbacRoleAuthorityDao;
import plus.wcj.heifer.boot.entity.rbac.role.RbacRoleAuthority;
import plus.wcj.heifer.boot.extension.service.impl.ServiceImpl;
import plus.wcj.heifer.boot.service.rbac.role.RbacRoleAuthorityService;

/**
 * <p>
 * 角色拥有功能权限关系表 服务实现类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@Service
public class RbacRoleAuthorityServiceImpl extends ServiceImpl<RbacRoleAuthorityDao, RbacRoleAuthority> implements RbacRoleAuthorityService {

}
