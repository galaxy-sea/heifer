package plus.wcj.heifer.plugin.rbac.service.impl;


import plus.wcj.heifer.common.mybatisplus.impl.ServiceImpl;
import plus.wcj.heifer.plugin.rbac.dao.RbacPermissionDao;
import plus.wcj.heifer.plugin.rbac.pojo.entity.RbacPermission;
import plus.wcj.heifer.plugin.rbac.service.RbacPermissionService;

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
