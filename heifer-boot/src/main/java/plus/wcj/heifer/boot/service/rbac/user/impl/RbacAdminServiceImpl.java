package plus.wcj.heifer.boot.service.rbac.user.impl;

import org.springframework.stereotype.Service;
import plus.wcj.heifer.boot.repository.dao.rbac.user.RbacAdminDao;
import plus.wcj.heifer.boot.entity.rbac.user.RbacAdmin;
import plus.wcj.heifer.boot.extension.service.impl.ServiceImpl;
import plus.wcj.heifer.boot.service.rbac.user.RbacAdminService;

/**
 * <p>
 * 管理员信息 服务实现类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@Service
public class RbacAdminServiceImpl extends ServiceImpl<RbacAdminDao, RbacAdmin, Long> implements RbacAdminService {

}
