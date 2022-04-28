package plus.wcj.heifer.plugin.iam.service.impl;


import plus.wcj.heifer.common.mybatisplus.impl.ServiceImpl;
import plus.wcj.heifer.plugin.iam.dao.IamDeptDao;
import plus.wcj.heifer.plugin.iam.entity.IamDept;
import plus.wcj.heifer.plugin.iam.service.IamDeptService;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-04-23
 */
@Service
public class IamDeptServiceImpl extends ServiceImpl<IamDeptDao, IamDept, Long> implements IamDeptService {

    @Override
    public List<IamDept> listByParentId(Long parentId) {
        return super.getBaseMapper().selectByParentId(parentId);
    }

    @Override
    public List<IamDept> listByIamTenantId(Long iamTenantId) {
        return super.getBaseMapper().selectByIamTenantId(iamTenantId);
    }

}
