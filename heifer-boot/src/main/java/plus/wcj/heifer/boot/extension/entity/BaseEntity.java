package plus.wcj.heifer.boot.extension.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import plus.wcj.heifer.boot.extension.validator.PostValid;
import plus.wcj.heifer.boot.extension.validator.PutValid;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/7/9
 */
public abstract class BaseEntity {

    /** 主键id */
    @ApiModelProperty(value = "主键id")
    @NotNull(groups = {PostValid.class, PutValid.class}, message = "id is null")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 租户id */
    @ApiModelProperty(value = "租户id")
    @TableField(value = "rbac_org_id", fill = FieldFill.INSERT_UPDATE)
    private Long rbacOrgId;

    /** 部门id */
    @ApiModelProperty(value = "部门id")
    @TableField(value = "rbac_dept_id", fill = FieldFill.INSERT_UPDATE)
    private Long rbacDeptId;

    @TableField(value = "create_time", fill = FieldFill.INSERT_UPDATE)
    private Date createTime;

    @TableField(value = "create_by", fill = FieldFill.INSERT_UPDATE)
    private Long createBy;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @TableField(value = "update_by", fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;
}
