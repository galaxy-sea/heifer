package plus.wcj.heifer.plugin.rbac.service.account.impl;


import plus.wcj.heifer.boot.mybatisplus.impl.ServiceImpl;
import plus.wcj.heifer.plugin.rbac.dao.account.RbacAccountDataPowerDao;
import plus.wcj.heifer.plugin.rbac.entity.account.RbacAccountDataPower;
import plus.wcj.heifer.plugin.rbac.service.account.RbacAccountDataPowerService;

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
