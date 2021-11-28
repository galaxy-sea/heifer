package plus.wcj.heifer.boot.entity.rbac.tenant;

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
 * 租户拥有的权限
 * </p>
 *
 * @author changjinwei
 * @since 2021-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@RedisHash("RbacTenantAuthority")
@TableName("rbac_tenant_authority")
@ApiModel(value = "RbacTenantAuthority对象", description = "租户拥有的权限")
public class RbacTenantAuthority implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @ApiModelProperty(value = "主键ID")
    @NotNull(groups = {PostValid.class, PutValid.class}, message = "id is null")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 组织id */
    @ApiModelProperty(value = "组织id")
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
