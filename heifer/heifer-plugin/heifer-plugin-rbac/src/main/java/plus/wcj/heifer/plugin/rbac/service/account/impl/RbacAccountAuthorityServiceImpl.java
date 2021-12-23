package plus.wcj.heifer.plugin.rbac.service.account.impl;


import plus.wcj.heifer.boot.mybatisplus.impl.ServiceImpl;
import plus.wcj.heifer.plugin.rbac.dao.account.RbacAccountAuthorityDao;
import plus.wcj.heifer.plugin.rbac.pojo.entity.account.RbacAccountAuthority;
import plus.wcj.heifer.plugin.rbac.service.account.RbacAccountAuthorityService;

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
