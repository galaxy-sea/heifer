package plus.wcj.heifer.boot.entity.tenant;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
 * 租户
 * </p>
 *
 * @author changjinwei
 * @since 2021-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tenant_org")
@ApiModel(value = "TenantOrgDo对象", description = "租户")
public class TenantOrgDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(groups = {PostValid.class, PutValid.class}, message = "id is null")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 租户名称 */
    @ApiModelProperty(value = "租户名称")
    @NotNull(groups = {PostValid.class}, message = "name is null")
    @TableField("name")
    private String name;

    @NotNull(groups = {PostValid.class}, message = "createBy is null")
    @TableField("create_by")
    private Long createBy;

    @NotNull(groups = {PostValid.class}, message = "updateBy is null")
    @TableField("update_by")
    private Long updateBy;

    @NotNull(groups = {PostValid.class}, message = "createTime is null")
    @TableField("create_time")
    private Date createTime;

    @NotNull(groups = {PostValid.class}, message = "updateTime is null")
    @TableField("update_time")
    private Date updateTime;


}
