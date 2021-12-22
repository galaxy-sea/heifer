package plus.wcj.heifer.plugin.rbac.service.account.impl;


import plus.wcj.heifer.boot.mybatisplus.impl.ServiceImpl;
import plus.wcj.heifer.plugin.rbac.dao.account.RbacAccountDao;
import plus.wcj.heifer.plugin.rbac.entity.account.RbacAccount;
import plus.wcj.heifer.plugin.rbac.service.account.RbacAccountService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 账户表 服务实现类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@Service
public class RbacAccountServiceImpl extends ServiceImpl<RbacAccountDao, RbacAccount, Long> implements RbacAccountService {

}
