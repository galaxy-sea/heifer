package plus.wcj.heifer.boot.service.rbac.user.impl;

import org.springframework.stereotype.Service;
import plus.wcj.heifer.boot.repository.dao.rbac.user.RbacUserAuthorityDao;
import plus.wcj.heifer.boot.entity.rbac.user.RbacUserAuthority;
import plus.wcj.heifer.boot.extension.service.impl.ServiceImpl;
import plus.wcj.heifer.boot.service.rbac.user.RbacUserAuthorityService;

/**
 * <p>
 * 用户拥有功能权限表 服务实现类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@Service
public class RbacUserAuthorityServiceImpl extends ServiceImpl<RbacUserAuthorityDao, RbacUserAuthority> implements RbacUserAuthorityService {

}
