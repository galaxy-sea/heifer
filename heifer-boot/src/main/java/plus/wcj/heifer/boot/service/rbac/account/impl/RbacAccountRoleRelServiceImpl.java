package plus.wcj.heifer.boot.service.rbac.account.impl;

import plus.wcj.heifer.boot.entity.rbac.account.RbacAccountRoleRel;
import plus.wcj.heifer.boot.repository.dao.rbac.account.RbacAccountRoleRelDao;
import plus.wcj.heifer.boot.service.rbac.account.RbacAccountRoleRelService;
import plus.wcj.heifer.boot.extension.service.impl.ServiceImpl;
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
