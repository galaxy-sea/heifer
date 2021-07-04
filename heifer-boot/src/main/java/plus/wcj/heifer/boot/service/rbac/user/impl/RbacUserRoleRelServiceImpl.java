package plus.wcj.heifer.boot.service.rbac.user.impl;

import org.springframework.stereotype.Service;
import plus.wcj.heifer.boot.dao.rbac.user.RbacUserRoleRelDao;
import plus.wcj.heifer.boot.entity.rbac.user.RbacUserRoleRel;
import plus.wcj.heifer.boot.extension.service.impl.ServiceImpl;
import plus.wcj.heifer.boot.service.rbac.user.RbacUserRoleRelService;

/**
 * <p>
 * 用户拥有角色关系表 服务实现类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@Service
public class RbacUserRoleRelServiceImpl extends ServiceImpl<RbacUserRoleRelDao, RbacUserRoleRel> implements RbacUserRoleRelService {

}
