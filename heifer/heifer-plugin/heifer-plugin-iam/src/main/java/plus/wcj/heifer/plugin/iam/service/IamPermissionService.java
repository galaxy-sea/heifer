package plus.wcj.heifer.plugin.iam.service;


import plus.wcj.heifer.common.mybatisplus.IService;
import plus.wcj.heifer.plugin.iam.entity.IamPermission;

import java.util.List;

/**
 * <p>
 * 功能权限 服务类
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-04-23
 */
public interface IamPermissionService extends IService<IamPermission, Long> {

    List<IamPermission> listByParentId(Long parentId);

}
