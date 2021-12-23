package plus.wcj.heifer.plugin.rbac.service.tenant.impl;


import plus.wcj.heifer.boot.mybatisplus.impl.ServiceImpl;
import plus.wcj.heifer.plugin.rbac.dao.org.RbacTenantAuthorityDao;
import plus.wcj.heifer.plugin.rbac.pojo.entity.tenant.RbacTenantAuthority;
import plus.wcj.heifer.plugin.rbac.service.tenant.RbacTenantAuthorityService;

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
