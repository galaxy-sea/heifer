package plus.wcj.heifer.plugin.rbac.service.role.impl;


import plus.wcj.heifer.common.mybatisplus.impl.ServiceImpl;
import plus.wcj.heifer.plugin.rbac.dao.role.RbacRoleAuthorityDao;
import plus.wcj.heifer.plugin.rbac.pojo.entity.role.RbacRoleAuthority;
import plus.wcj.heifer.plugin.rbac.service.role.RbacRoleAuthorityService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色拥有功能权限关系表 服务实现类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@Service
public class RbacRoleAuthorityServiceImpl extends ServiceImpl<RbacRoleAuthorityDao, RbacRoleAuthority, Long> implements RbacRoleAuthorityService {

}
