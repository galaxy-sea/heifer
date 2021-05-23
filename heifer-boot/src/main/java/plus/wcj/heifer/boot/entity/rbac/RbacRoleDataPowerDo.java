package plus.wcj.heifer.boot.entity.rbac;

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

/**
 * <p>
 * 角色部门权限
 * </p>
 *
 * @author changjinwei
 * @since 2021-05-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("rbac_role_data_power")
@ApiModel(value="RbacRoleDataPowerDo对象", description="角色部门权限")
public class RbacRoleDataPowerDo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键id */
    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 角色id */
    @ApiModelProperty(value = "角色id")
    @TableField("rbac_role_id")
    private Long rbacRoleId;

    /** 部门id */
    @ApiModelProperty(value = "部门id")
    @TableField("rbac_dept_id")
    private Long rbacDeptId;


}
