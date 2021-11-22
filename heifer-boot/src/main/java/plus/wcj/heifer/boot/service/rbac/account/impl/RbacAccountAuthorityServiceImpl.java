package plus.wcj.heifer.boot.service.rbac.account.impl;

import plus.wcj.heifer.boot.entity.rbac.account.RbacAccountAuthority;
import plus.wcj.heifer.boot.repository.dao.rbac.account.RbacAccountAuthorityDao;
import plus.wcj.heifer.boot.service.rbac.account.RbacAccountAuthorityService;
import plus.wcj.heifer.boot.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账户拥有功能权限表 服务实现类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@Service
public class RbacAccountAuthorityServiceImpl extends ServiceImpl<RbacAccountAuthorityDao, RbacAccountAuthority, Long> implements RbacAccountAuthorityService {

}
