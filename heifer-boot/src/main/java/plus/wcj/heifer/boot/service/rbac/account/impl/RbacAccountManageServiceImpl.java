package plus.wcj.heifer.boot.service.rbac.account.impl;

import plus.wcj.heifer.boot.entity.rbac.account.RbacAccountManage;
import plus.wcj.heifer.boot.repository.dao.rbac.account.RbacAccountManageDao;
import plus.wcj.heifer.boot.service.rbac.account.RbacAccountManageService;
import plus.wcj.heifer.boot.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账户与租户的绑定关系 服务实现类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@Service
public class RbacAccountManageServiceImpl extends ServiceImpl<RbacAccountManageDao, RbacAccountManage, Long> implements RbacAccountManageService {

}
