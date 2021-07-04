package plus.wcj.heifer.boot.service.rbac.user.impl;

import org.springframework.stereotype.Service;
import plus.wcj.heifer.boot.dao.rbac.user.RbacUserDao;
import plus.wcj.heifer.boot.entity.rbac.user.RbacUser;
import plus.wcj.heifer.boot.extension.service.impl.ServiceImpl;
import plus.wcj.heifer.boot.service.rbac.user.RbacUserService;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@Service
public class RbacUserServiceImpl extends ServiceImpl<RbacUserDao, RbacUser> implements RbacUserService {

}
