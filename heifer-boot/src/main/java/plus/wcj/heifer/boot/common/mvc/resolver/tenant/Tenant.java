package plus.wcj.heifer.boot.common.mvc.resolver.tenant;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/6/28
 */
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Tenant {
    /** 用户id */
    private final Long userId;

    /** 用户id */
    private final String username;

    /** 组织id */
    private final Long orgId;

    /** 部门id */
    private final Long deptId;

    /** 数据权限 */
    private final String dataPowers;

    /** org权限下的全部数据权限 */
    private final boolean allPower;
}
