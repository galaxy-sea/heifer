package plus.wcj.heifer.boot.entity.rbac.user;

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
 * 用户拥有功能权限表
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("rbac_user_authority")
@ApiModel(value = "RbacUserAuthority对象", description = "用户拥有功能权限表")
public class RbacUserAuthority implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @ApiModelProperty(value = "主键ID")
    @NotNull(groups = {PostValid.class, PutValid.class}, message = "id is null")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 用户id */
    @ApiModelProperty(value = "用户id")
    @NotNull(groups = {PostValid.class}, message = "rbacUserId is null")
    @TableField("rbac_user_id")
    private Long rbacUserId;

    /** 功能权限id */
    @ApiModelProperty(value = "功能权限id")
    @NotNull(groups = {PostValid.class}, message = "rbacPermissionId is null")
    @TableField("rbac_permission_id")
    private Long rbacPermissionId;

    @TableField("create_time")
    private Date createTime;

    @NotNull(groups = {PostValid.class}, message = "createBy is null")
    @TableField("create_by")
    private Long createBy;


}
