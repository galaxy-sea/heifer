package plus.wcj.heifer.plugin.rbac.service.tenant.impl;


import plus.wcj.heifer.boot.mybatisplus.impl.ServiceImpl;
import plus.wcj.heifer.plugin.rbac.dao.org.RbacDeptDao;
import plus.wcj.heifer.plugin.rbac.entity.tenant.RbacDept;
import plus.wcj.heifer.plugin.rbac.service.tenant.RbacDeptService;

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
