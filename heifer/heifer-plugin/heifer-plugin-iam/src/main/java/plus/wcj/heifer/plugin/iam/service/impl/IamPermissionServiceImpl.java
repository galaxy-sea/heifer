package plus.wcj.heifer.plugin.iam.service.impl;


import plus.wcj.heifer.common.mybatisplus.impl.ServiceImpl;
import plus.wcj.heifer.plugin.iam.dao.IamPermissionDao;
import plus.wcj.heifer.plugin.iam.entity.IamPermission;
import plus.wcj.heifer.plugin.iam.service.IamPermissionService;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 功能权限 服务实现类
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-04-23
 */
@Service
public class IamPermissionServiceImpl extends ServiceImpl<IamPermissionDao, IamPermission, Long> implements IamPermissionService {

    @Override
    public List<IamPermission> listByParentId(Long parentId) {
        return super.getBaseMapper().selectByParentId(parentId);
    }

}
