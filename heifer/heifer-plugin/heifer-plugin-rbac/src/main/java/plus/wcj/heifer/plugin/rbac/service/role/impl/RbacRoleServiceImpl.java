package plus.wcj.heifer.plugin.rbac.service.role.impl;


import plus.wcj.heifer.boot.mybatisplus.impl.ServiceImpl;
import plus.wcj.heifer.plugin.rbac.dao.role.RbacRoleDao;
import plus.wcj.heifer.plugin.rbac.pojo.entity.role.RbacRole;
import plus.wcj.heifer.plugin.rbac.service.role.RbacRoleService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@Service
public class RbacRoleServiceImpl extends ServiceImpl<RbacRoleDao, RbacRole, Long> implements RbacRoleService {

}
