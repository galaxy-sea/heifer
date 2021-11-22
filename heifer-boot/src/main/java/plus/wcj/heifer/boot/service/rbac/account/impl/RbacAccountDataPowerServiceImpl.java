package plus.wcj.heifer.boot.service.rbac.account.impl;

import plus.wcj.heifer.boot.entity.rbac.account.RbacAccountDataPower;
import plus.wcj.heifer.boot.repository.dao.rbac.account.RbacAccountDataPowerDao;
import plus.wcj.heifer.boot.service.rbac.account.RbacAccountDataPowerService;
import plus.wcj.heifer.boot.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账户数据权限 服务实现类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@Service
public class RbacAccountDataPowerServiceImpl extends ServiceImpl<RbacAccountDataPowerDao, RbacAccountDataPower, Long> implements RbacAccountDataPowerService {

}
