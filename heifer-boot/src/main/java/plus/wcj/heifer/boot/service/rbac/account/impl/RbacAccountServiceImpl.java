package plus.wcj.heifer.boot.service.rbac.account.impl;

import plus.wcj.heifer.boot.entity.rbac.account.RbacAccount;
import plus.wcj.heifer.boot.repository.dao.rbac.account.RbacAccountDao;
import plus.wcj.heifer.boot.service.rbac.account.RbacAccountService;
import plus.wcj.heifer.boot.extension.service.impl.ServiceImpl;
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
