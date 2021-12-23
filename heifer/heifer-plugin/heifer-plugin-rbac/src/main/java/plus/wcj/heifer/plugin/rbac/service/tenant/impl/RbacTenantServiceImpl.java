package plus.wcj.heifer.plugin.rbac.service.tenant.impl;


import plus.wcj.heifer.boot.mybatisplus.impl.ServiceImpl;
import plus.wcj.heifer.plugin.rbac.dao.org.RbacTenantDao;
import plus.wcj.heifer.plugin.rbac.pojo.entity.tenant.RbacTenant;
import plus.wcj.heifer.plugin.rbac.service.tenant.RbacTenantService;

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
