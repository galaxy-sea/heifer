package plus.wcj.heifer.boot.service.rbac.tenant.impl;

import plus.wcj.heifer.boot.entity.rbac.tenant.RbacTenantAuthority;
import plus.wcj.heifer.boot.repository.dao.rbac.org.RbacTenantAuthorityDao;
import plus.wcj.heifer.boot.service.rbac.tenant.RbacTenantAuthorityService;
import plus.wcj.heifer.boot.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 租户拥有的权限 服务实现类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@Service
public class RbacTenantAuthorityServiceImpl extends ServiceImpl<RbacTenantAuthorityDao, RbacTenantAuthority, Long> implements RbacTenantAuthorityService {

}
