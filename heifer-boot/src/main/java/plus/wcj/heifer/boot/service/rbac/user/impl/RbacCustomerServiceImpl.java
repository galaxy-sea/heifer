package plus.wcj.heifer.boot.service.rbac.user.impl;

import org.springframework.stereotype.Service;
import plus.wcj.heifer.boot.repository.dao.rbac.user.RbacCustomerDao;
import plus.wcj.heifer.boot.entity.rbac.user.RbacCustomer;
import plus.wcj.heifer.boot.extension.service.impl.ServiceImpl;
import plus.wcj.heifer.boot.service.rbac.user.RbacCustomerService;

/**
 * <p>
 * 顾客信息 服务实现类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@Service
public class RbacCustomerServiceImpl extends ServiceImpl<RbacCustomerDao, RbacCustomer, Long> implements RbacCustomerService {

}
