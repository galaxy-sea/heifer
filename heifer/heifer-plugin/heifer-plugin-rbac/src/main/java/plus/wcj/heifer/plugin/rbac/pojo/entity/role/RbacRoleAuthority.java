package plus.wcj.heifer.plugin.rbac.pojo.entity.role;

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

import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 角色拥有功能权限关系表
 * </p>
 *
 * @author changjinwei
 * @since 2021-11-28
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

    /** 租户id */
    @ApiModelProperty(value = "租户id")
    @NotNull(groups = {PostValid.class}, message = "rbacTenantId is null")
    @TableField("rbac_tenant_id")
    private Long rbacTenantId;

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
