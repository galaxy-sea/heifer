package plus.wcj.heifer.plugin.iam.service;


import plus.wcj.heifer.common.mybatisplus.IService;
import plus.wcj.heifer.plugin.iam.entity.IamDept;

import java.util.List;

/**
 * <p>
 * 部门 服务类
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-04-23
 */
public interface IamDeptService extends IService<IamDept, Long> {

    List<IamDept> listByParentId(Long parentId);

    List<IamDept> listByIamTenantId(Long iamTenantId);

}
