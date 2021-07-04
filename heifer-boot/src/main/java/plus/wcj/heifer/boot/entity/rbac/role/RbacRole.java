package plus.wcj.heifer.boot.entity.rbac.role;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import plus.wcj.heifer.boot.extension.validator.PostValid;
import plus.wcj.heifer.boot.extension.validator.PutValid;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("rbac_role")
@ApiModel(value = "RbacRole对象", description = "角色表")
public class RbacRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @ApiModelProperty(value = "主键ID")
    @NotNull(groups = {PostValid.class, PutValid.class}, message = "id is null")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 租户id */
    @ApiModelProperty(value = "租户id")
    @NotNull(groups = {PostValid.class}, message = "rbacOrgId is null")
    @TableField("rbac_org_id")
    private Long rbacOrgId;

    /** 名称 */
    @ApiModelProperty(value = "名称")
    @NotNull(groups = {PostValid.class}, message = "name is null")
    @TableField("name")
    private String name;

    @TableField("create_time")
    private Date createTime;

    @NotNull(groups = {PostValid.class}, message = "createBy is null")
    @TableField("create_by")
    private Long createBy;

    @TableField("update_time")
    private Date updateTime;

    @TableField("update_by")
    private Long updateBy;


}
