package plus.wcj.heifer.boot.service.rbac.tenant.impl;

import plus.wcj.heifer.boot.entity.rbac.tenant.RbacTenant;
import plus.wcj.heifer.boot.repository.dao.rbac.org.RbacTenantDao;
import plus.wcj.heifer.boot.service.rbac.tenant.RbacTenantService;
import plus.wcj.heifer.boot.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 租户 服务实现类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@Service
public class RbacTenantServiceImpl extends ServiceImpl<RbacTenantDao, RbacTenant, Long> implements RbacTenantService {

}
