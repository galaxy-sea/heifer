package plus.wcj.heifer.plugin.iam.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 租户
 * </p>
 *
 * @author changjinwei
 * @since 2021-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TenantDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long id;

    /** 租户名称 */
    private String name;
}