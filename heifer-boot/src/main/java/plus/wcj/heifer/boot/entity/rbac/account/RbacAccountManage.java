package plus.wcj.heifer.boot.entity.rbac.account;

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
 * 账户与租户的绑定关系
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@RedisHash("RbacAccountManage")
@TableName("rbac_account_manage")
@ApiModel(value = "RbacAccountManage对象", description = "账户与租户的绑定关系")
public class RbacAccountManage implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @ApiModelProperty(value = "主键ID")
    @NotNull(groups = {PostValid.class, PutValid.class}, message = "id is null")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 账户id */
    @ApiModelProperty(value = "账户id")
    @NotNull(groups = {PostValid.class}, message = "rbacAccountId is null")
    @TableField("rbac_account_id")
    private Long rbacAccountId;

    /** 租户id */
    @ApiModelProperty(value = "租户id")
    @NotNull(groups = {PostValid.class}, message = "rbacTenantId is null")
    @TableField("rbac_tenant_id")
    private Long rbacTenantId;

    /** 部门id */
    @ApiModelProperty(value = "部门id")
    @NotNull(groups = {PostValid.class}, message = "rbacDeptId is null")
    @TableField("rbac_dept_id")
    private Long rbacDeptId;

    @TableField("create_time")
    private Date createTime;

    @TableField("create_by")
    private Long createBy;


}
