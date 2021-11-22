package plus.wcj.heifer.boot.entity.rbac.role;

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
import org.springframework.data.redis.core.RedisHash;


import plus.wcj.heifer.boot.extension.validator.PostValid;
import plus.wcj.heifer.boot.extension.validator.PutValid;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 角色拥有功能权限关系表
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@RedisHash("RbacRoleAuthority")
@TableName("rbac_role_authority")
@ApiModel(value = "RbacRoleAuthority对象", description = "角色拥有功能权限关系表")
public class RbacRoleAuthority implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @ApiModelProperty(value = "主键ID")
    @NotNull(groups = {PostValid.class, PutValid.class}, message = "id is null")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 角色id */
    @ApiModelProperty(value = "角色id")
    @NotNull(groups = {PostValid.class}, message = "rbacRoleId is null")
    @TableField("rbac_role_id")
    private Long rbacRoleId;

    /** 功能权限id */
    @ApiModelProperty(value = "功能权限id")
    @NotNull(groups = {PostValid.class}, message = "rbacPermissionId is null")
    @TableField("rbac_permission_id")
    private Long rbacPermissionId;

    @TableField("create_time")
    private Date createTime;

    @TableField("create_by")
    private Long createBy;


}
