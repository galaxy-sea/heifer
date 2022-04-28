package plus.wcj.heifer.plugin.iam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import plus.wcj.heifer.metadata.annotation.PostValid;
import plus.wcj.heifer.metadata.annotation.PutValid;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 角色数据权限
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("iam_role_data_power")
@ApiModel(value = "IamRoleDataPower对象", description = "角色数据权限")
public class IamRoleDataPower implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键id */
    @ApiModelProperty(value = "主键id")
    @NotNull(groups = {PutValid.class}, message = "id is null")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 角色id */
    @ApiModelProperty(value = "角色id")
    @NotNull(groups = {PostValid.class}, message = "iamRoleId is null")
    @TableField("iam_role_id")
    private Long iamRoleId;

    /** 部门id */
    @ApiModelProperty(value = "部门id")
    @NotNull(groups = {PostValid.class}, message = "iamDeptId is null")
    @TableField("iam_dept_id")
    private Long iamDeptId;

    /** 租户id */
    @ApiModelProperty(value = "租户id")
    @NotNull(groups = {PostValid.class}, message = "iamTenantId is null")
    @TableField("iam_tenant_id")
    private Long iamTenantId;

    @TableField("create_time")
    private Date createTime;

    @TableField("create_by")
    private Long createBy;


}
