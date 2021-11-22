package plus.wcj.heifer.boot.service.rbac.impl;

import plus.wcj.heifer.boot.entity.rbac.RbacPermission;
import plus.wcj.heifer.boot.repository.dao.rbac.RbacPermissionDao;
import plus.wcj.heifer.boot.service.rbac.RbacPermissionService;
import plus.wcj.heifer.boot.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 功能权限 服务实现类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@Service
public class RbacPermissionServiceImpl extends ServiceImpl<RbacPermissionDao, RbacPermission, Long> implements RbacPermissionService {

}
