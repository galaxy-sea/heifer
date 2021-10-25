package plus.wcj.heifer.boot.service.rbac.user.impl;

import org.springframework.stereotype.Service;
import plus.wcj.heifer.boot.repository.dao.rbac.user.RbacUserDataPowerDao;
import plus.wcj.heifer.boot.entity.rbac.user.RbacUserDataPower;
import plus.wcj.heifer.boot.extension.service.impl.ServiceImpl;
import plus.wcj.heifer.boot.service.rbac.user.RbacUserDataPowerService;

/**
 * <p>
 * 用户数据权限 服务实现类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@Service
public class RbacUserDataPowerServiceImpl extends ServiceImpl<RbacUserDataPowerDao, RbacUserDataPower, Long> implements RbacUserDataPowerService {

}
