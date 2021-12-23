package plus.wcj.heifer.plugin.rbac.service.account.impl;


import plus.wcj.heifer.boot.mybatisplus.impl.ServiceImpl;
import plus.wcj.heifer.plugin.rbac.dao.account.RbacAccountManageDao;
import plus.wcj.heifer.plugin.rbac.pojo.entity.account.RbacAccountManage;
import plus.wcj.heifer.plugin.rbac.service.account.RbacAccountManageService;

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
