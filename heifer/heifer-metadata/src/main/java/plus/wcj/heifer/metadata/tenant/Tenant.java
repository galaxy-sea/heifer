package plus.wcj.heifer.metadata.tenant;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/6/28
 */
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class Tenant {
    /** 用户id */
    private final Long accountId;

    /** 用户id */
    private final String username;

    /** 组织id */
    private final Long tenantId;

    /** 部门id */
    private final Long deptId;

    /** 数据权限 */
    private final List<Long> dataPowers;
    /** 是否拥有tenant的全部dept权限，超级管理员权限 */
    private final boolean tenantDataPower;
    /** 是否拥有当前tenant下的全部dept权限，租户管理员权限 */
    private final boolean deptDataPower;
}
