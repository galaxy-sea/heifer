package plus.wcj.heifer.boot.common.mvc.resolver.tenant;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Collection;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/6/28
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Tenant {
    /** 用户id */
    private Long userId;

    /** 用户id */
    private String username;

    /** 组织id */
    private Long orgId;

    /** 部门id */
    private Long deptId;

    /** 数据权限 */
    private Collection<Long> dataPowers;

    /** 功能权限 */
    @Deprecated
    private Collection<String> authority;

}
