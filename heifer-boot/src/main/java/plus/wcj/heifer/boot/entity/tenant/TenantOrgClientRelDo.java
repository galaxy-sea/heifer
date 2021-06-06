package plus.wcj.heifer.boot.entity.tenant;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import plus.wcj.heifer.boot.extension.validator.PostValid;
import plus.wcj.heifer.boot.extension.validator.PutValid;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 租户客户端
 * </p>
 *
 * @author changjinwei
 * @since 2021-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tenant_org_client_rel")
@ApiModel(value = "TenantOrgClientRelDo对象", description = "租户客户端")
public class TenantOrgClientRelDo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键id */
    @ApiModelProperty(value = "主键id")
    @NotNull(groups = {PostValid.class, PutValid.class}, message = "id is null")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 租户id */
    @ApiModelProperty(value = "租户id")
    @NotNull(groups = {PostValid.class}, message = "tenantOrgId is null")
    @TableField("tenant_org_id")
    private Long tenantOrgId;

    /** 客户端id */
    @ApiModelProperty(value = "客户端id")
    @NotNull(groups = {PostValid.class}, message = "tenantClientId is null")
    @TableField("tenant_client_id")
    private Long tenantClientId;


}
