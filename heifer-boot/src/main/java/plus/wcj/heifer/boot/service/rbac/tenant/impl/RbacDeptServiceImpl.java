package plus.wcj.heifer.boot.service.rbac.tenant.impl;

import plus.wcj.heifer.boot.entity.rbac.tenant.RbacDept;
import plus.wcj.heifer.boot.repository.dao.rbac.org.RbacDeptDao;
import plus.wcj.heifer.boot.service.rbac.tenant.RbacDeptService;
import plus.wcj.heifer.boot.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@Service
public class RbacDeptServiceImpl extends ServiceImpl<RbacDeptDao, RbacDept, Long> implements RbacDeptService {

}
