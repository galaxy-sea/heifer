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
 * 账户拥有角色关系表
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@RedisHash("RbacAccountRoleRel")
@TableName("rbac_account_role_rel")
@ApiModel(value = "RbacAccountRoleRel对象", description = "账户拥有角色关系表")
public class RbacAccountRoleRel implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键id */
    @ApiModelProperty(value = "主键id")
    @NotNull(groups = {PostValid.class, PutValid.class}, message = "id is null")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 账户id */
    @ApiModelProperty(value = "账户id")
    @NotNull(groups = {PostValid.class}, message = "rbacAccountId is null")
    @TableField("rbac_account_id")
    private Long rbacAccountId;

    /** 角色id */
    @ApiModelProperty(value = "角色id")
    @NotNull(groups = {PostValid.class}, message = "rbacRoleId is null")
    @TableField("rbac_role_id")
    private Long rbacRoleId;

    @TableField("create_time")
    private Date createTime;

    @TableField("create_by")
    private Long createBy;


}
