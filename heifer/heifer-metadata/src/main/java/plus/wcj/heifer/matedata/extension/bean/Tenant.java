package plus.wcj.heifer.matedata.extension.bean;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/6/28
 */
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class Tenant {
    /** 用户id */
    private final Long userId;

    /** 用户id */
    private final String username;

    /** 组织id */
    private final Long tenantId;

    /** 部门id */
    private final Long deptId;

    /** 数据权限 */
    private final String dataPowers;

    /** org权限下的全部数据权限 */
    private final boolean allPower;
}
