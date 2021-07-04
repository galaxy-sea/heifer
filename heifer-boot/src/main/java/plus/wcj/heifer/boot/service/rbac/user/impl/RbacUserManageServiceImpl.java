package plus.wcj.heifer.boot.service.rbac.user.impl;

import org.springframework.stereotype.Service;
import plus.wcj.heifer.boot.dao.rbac.user.RbacUserManageDao;
import plus.wcj.heifer.boot.entity.rbac.user.RbacUserManage;
import plus.wcj.heifer.boot.extension.service.impl.ServiceImpl;
import plus.wcj.heifer.boot.service.rbac.user.RbacUserManageService;

/**
 * <p>
 * 用户是否拥有全部数据权限和功能权限 服务实现类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@Service
public class RbacUserManageServiceImpl extends ServiceImpl<RbacUserManageDao, RbacUserManage> implements RbacUserManageService {

}
