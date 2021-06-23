package plus.wcj.heifer.boot.entity.rbac;

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
 * 用户部门权限
 * </p>
 *
 * @author changjinwei
 * @since 2021-06-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("rbac_user_data_power")
@ApiModel(value = "RbacUserDataPowerDo对象", description = "用户部门权限")
public class RbacUserDataPowerDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(groups = {PostValid.class, PutValid.class}, message = "id is null")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 用户id */
    @ApiModelProperty(value = "用户id")
    @NotNull(groups = {PostValid.class}, message = "rbacUserId is null")
    @TableField("rbac_user_id")
    private Long rbacUserId;

    /** 部门id */
    @ApiModelProperty(value = "部门id")
    @NotNull(groups = {PostValid.class}, message = "rbacDeptId is null")
    @TableField("rbac_dept_id")
    private Long rbacDeptId;

    /** 租户id */
    @ApiModelProperty(value = "租户id")
    @TableField("rbac_org_id")
    private Long rbacOrgId;

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
