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
 * 用户功能权限表
 * </p>
 *
 * @author changjinwei
 * @since 2021-05-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("rbac_user_permission_rel")
@ApiModel(value="RbacUserPermissionRelDo对象", description="用户功能权限表")
public class RbacUserPermissionRelDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 主键id */
    @ApiModelProperty(value = "主键id")
    @TableField("rbac_user_id")
    private Long rbacUserId;

    /** 功能权限id */
    @ApiModelProperty(value = "功能权限id")
    @TableField("rbac_permission_id")
    private Long rbacPermissionId;


}
