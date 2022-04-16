package plus.wcj.heifer.plugin.rbac.service.account.impl;


import plus.wcj.heifer.common.mybatisplus.impl.ServiceImpl;
import plus.wcj.heifer.plugin.rbac.dao.account.RbacAccountRoleRelDao;
import plus.wcj.heifer.plugin.rbac.pojo.entity.account.RbacAccountRoleRel;
import plus.wcj.heifer.plugin.rbac.service.account.RbacAccountRoleRelService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 账户拥有角色关系表 服务实现类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@Service
public class RbacAccountRoleRelServiceImpl extends ServiceImpl<RbacAccountRoleRelDao, RbacAccountRoleRel, Long> implements RbacAccountRoleRelService {

}
