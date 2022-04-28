package plus.wcj.heifer.plugin.iam.service.impl;


import plus.wcj.heifer.common.mybatisplus.impl.ServiceImpl;
import plus.wcj.heifer.plugin.iam.dao.IamTenantDao;
import plus.wcj.heifer.plugin.iam.entity.IamTenant;
import plus.wcj.heifer.plugin.iam.service.IamTenantService;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 租户 服务实现类
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-04-23
 */
@Service
public class IamTenantServiceImpl extends ServiceImpl<IamTenantDao, IamTenant, Long> implements IamTenantService {

}
