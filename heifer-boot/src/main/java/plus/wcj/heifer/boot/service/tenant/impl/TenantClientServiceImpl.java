package plus.wcj.heifer.boot.service.tenant.impl;

import plus.wcj.heifer.boot.entity.tenant.TenantClientDo;
import plus.wcj.heifer.boot.dao.tenant.TenantClientDao;
import plus.wcj.heifer.boot.service.tenant.TenantClientService;
import plus.wcj.heifer.boot.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户端 服务实现类
 * </p>
 *
 * @author changjinwei
 * @since 2021-06-06
 */
@Service
public class TenantClientServiceImpl extends ServiceImpl<TenantClientDao, TenantClientDo> implements TenantClientService {

}
