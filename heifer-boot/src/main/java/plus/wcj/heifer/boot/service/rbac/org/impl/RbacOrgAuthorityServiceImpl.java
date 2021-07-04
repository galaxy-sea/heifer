package plus.wcj.heifer.boot.service.rbac.org.impl;

import org.springframework.stereotype.Service;
import plus.wcj.heifer.boot.dao.rbac.org.RbacOrgAuthorityDao;
import plus.wcj.heifer.boot.entity.rbac.org.RbacOrgAuthority;
import plus.wcj.heifer.boot.extension.service.impl.ServiceImpl;
import plus.wcj.heifer.boot.service.rbac.org.RbacOrgAuthorityService;

/**
 * <p>
 * 租户拥有的权限 服务实现类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@Service
public class RbacOrgAuthorityServiceImpl extends ServiceImpl<RbacOrgAuthorityDao, RbacOrgAuthority> implements RbacOrgAuthorityService {

}
