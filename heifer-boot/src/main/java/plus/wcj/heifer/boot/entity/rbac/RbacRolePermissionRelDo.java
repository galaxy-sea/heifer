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
 * 角色功能权限关系表
 * </p>
 *
 * @author changjinwei
 * @since 2021-05-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("rbac_role_permission_rel")
@ApiModel(value="RbacRolePermissionRelDo对象", description="角色功能权限关系表")
public class RbacRolePermissionRelDo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 角色id */
    @ApiModelProperty(value = "角色id")
    @TableField("rdac_role_id")
    private Long rdacRoleId;

    /** 功能权限id */
    @ApiModelProperty(value = "功能权限id")
    @TableField("rdac_permission_id")
    private Long rdacPermissionId;


}
