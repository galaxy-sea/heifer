package plus.wcj.heifer.common.security.userdetails.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 账户与租户的绑定关系
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RbacAccountManageDto implements Serializable {

    private static final long serialVersionUID = 1L;
    /** 租户id */
    private Long rbacTenantId;

    /** 部门id */
    private Long rbacDeptId;
}
