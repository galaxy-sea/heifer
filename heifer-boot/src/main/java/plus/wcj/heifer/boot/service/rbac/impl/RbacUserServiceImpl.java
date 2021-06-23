package plus.wcj.heifer.boot.service.rbac.impl;

import plus.wcj.heifer.boot.entity.rbac.RbacUserDo;
import plus.wcj.heifer.boot.dao.rbac.RbacUserDao;
import plus.wcj.heifer.boot.service.rbac.RbacUserService;
import plus.wcj.heifer.boot.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author changjinwei
 * @since 2021-06-23
 */
@Service
public class RbacUserServiceImpl extends ServiceImpl<RbacUserDao, RbacUserDo> implements RbacUserService {

}
