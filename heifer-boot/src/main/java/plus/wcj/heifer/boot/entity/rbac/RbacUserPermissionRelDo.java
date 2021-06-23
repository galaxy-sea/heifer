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

/**
 * <p>
 * 用户功能权限表
 * </p>
 *
 * @author changjinwei
 * @since 2021-06-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("rbac_user_permission_rel")
@ApiModel(value = "RbacUserPermissionRelDo对象", description = "用户功能权限表")
public class RbacUserPermissionRelDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(groups = {PostValid.class, PutValid.class}, message = "id is null")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 主键id */
    @ApiModelProperty(value = "主键id")
    @NotNull(groups = {PostValid.class}, message = "rbacUserId is null")
    @TableField("rbac_user_id")
    private Long rbacUserId;

    /** 租户id */
    @ApiModelProperty(value = "租户id")
    @TableField("rbac_org_id")
    private Long rbacOrgId;

    /** 功能权限id */
    @ApiModelProperty(value = "功能权限id")
    @NotNull(groups = {PostValid.class}, message = "rbacPermissionId is null")
    @TableField("rbac_permission_id")
    private Long rbacPermissionId;


}
