package plus.wcj.heifer.boot.entity.rbac;

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
import plus.wcj.heifer.boot.extension.validator.PostValid;
import plus.wcj.heifer.boot.extension.validator.PutValid;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author changjinwei
 * @since 2021-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("rbac_user")
@ApiModel(value = "RbacUserDo对象", description = "用户表")
public class RbacUserDo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @ApiModelProperty(value = "主键ID")
    @NotNull(groups = {PostValid.class, PutValid.class}, message = "id is null")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 租户id */
    @ApiModelProperty(value = "租户id")
    @NotNull(groups = {PostValid.class}, message = "tenantOrgId is null")
    @TableField("tenant_org_id")
    private Long tenantOrgId;

    /** 部门id */
    @ApiModelProperty(value = "部门id")
    @NotNull(groups = {PostValid.class}, message = "rbacDeptId is null")
    @TableField("rbac_dept_id")
    private Long rbacDeptId;

    /** 用户名 */
    @ApiModelProperty(value = "用户名")
    @NotNull(groups = {PostValid.class}, message = "username is null")
    @TableField("username")
    private String username;

    /** 手机号 */
    @ApiModelProperty(value = "手机号")
    @NotNull(groups = {PostValid.class}, message = "phone is null")
    @TableField("phone")
    private String phone;

    /** 邮箱 */
    @ApiModelProperty(value = "邮箱")
    @NotNull(groups = {PostValid.class}, message = "email is null")
    @TableField("email")
    private String email;

    /** 密码 */
    @ApiModelProperty(value = "密码")
    @NotNull(groups = {PostValid.class}, message = "password is null")
    @TableField("password")
    private String password;

    /** 昵称 */
    @ApiModelProperty(value = "昵称")
    @NotNull(groups = {PostValid.class}, message = "nickname is null")
    @TableField("nickname")
    private String nickname;

    /** 指示用户的帐户是否已过期。过期的帐户无法通过身份验证。返回值：true如果用户的帐户是否有效（即未过期），false如果不再有效（即到期） */
    @ApiModelProperty(value = "指示用户的帐户是否已过期。过期的帐户无法通过身份验证。返回值：true如果用户的帐户是否有效（即未过期），false如果不再有效（即到期）")
    @NotNull(groups = {PostValid.class}, message = "accountNonExpired is null")
    @TableField("is_account_non_expired")
    private Boolean accountNonExpired;

    /** 指示用户是锁定还是解锁。锁定的用户无法通过身份验证。返回值：true如果用户没有被锁定，false否则 */
    @ApiModelProperty(value = "指示用户是锁定还是解锁。锁定的用户无法通过身份验证。返回值：true如果用户没有被锁定，false否则")
    @NotNull(groups = {PostValid.class}, message = "accountNonLocked is null")
    @TableField("is_account_non_locked")
    private Boolean accountNonLocked;

    /** 指示用户的凭据（密码）是否已过期。过期的凭据会阻止身份验证。返回值：true如果用户的证书是有效的（即非到期），false如果不再有效（即到期 */
    @ApiModelProperty(value = "指示用户的凭据（密码）是否已过期。过期的凭据会阻止身份验证。返回值：true如果用户的证书是有效的（即非到期），false如果不再有效（即到期")
    @NotNull(groups = {PostValid.class}, message = "credentialsNonExpired is null")
    @TableField("is_credentials_non_expired")
    private Boolean credentialsNonExpired;

    /** 指示用户是启用还是禁用。禁用的用户无法通过身份验证。返回值：true如果用户已启用，false否则 */
    @ApiModelProperty(value = "指示用户是启用还是禁用。禁用的用户无法通过身份验证。返回值：true如果用户已启用，false否则")
    @NotNull(groups = {PostValid.class}, message = "enabled is null")
    @TableField("is_enabled")
    private Boolean enabled;

    @NotNull(groups = {PostValid.class}, message = "createBy is null")
    @TableField("create_by")
    private Long createBy;

    @NotNull(groups = {PostValid.class}, message = "updateBy is null")
    @TableField("update_by")
    private Long updateBy;

    @NotNull(groups = {PostValid.class}, message = "createTime is null")
    @TableField("create_time")
    private Date createTime;

    @NotNull(groups = {PostValid.class}, message = "updateTime is null")
    @TableField("update_time")
    private Date updateTime;


}
