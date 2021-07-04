package plus.wcj.heifer.boot.common.security.userdetails.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author changjinwei
 * @since 2021-05-23
 */
@Data
@Accessors(chain = true)
@TableName("rbac_user")
@ApiModel(value = "RbacUserDo对象", description = "用户表")
public class RbacUserDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 租户id */
    @ApiModelProperty(value = "租户id")
    @TableField("rbac_org_id")
    private Long rbacOrgId;

    /** 用户名 */
    @ApiModelProperty(value = "用户名")
    @TableField("username")
    private String username;

    /** 手机号 */
    @ApiModelProperty(value = "手机号")
    @TableField("phone")
    private String phone;

    /** 邮箱 */
    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    /** 密码 */
    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    /** 昵称 */
    @ApiModelProperty(value = "昵称")
    @TableField("nickname")
    private String nickname;

    /** 指示用户的帐户是否已过期。过期的帐户无法通过身份验证。返回值：true如果用户的帐户是否有效（即未过期），false如果不再有效（即到期） */
    @ApiModelProperty(value = "指示用户的帐户是否已过期。过期的帐户无法通过身份验证。返回值：true如果用户的帐户是否有效（即未过期），false如果不再有效（即到期）")
    @TableField("is_account_non_expired")
    private Boolean isAccountNonExpired;

    /** 指示用户是锁定还是解锁。锁定的用户无法通过身份验证。返回值：true如果用户没有被锁定，false否则 */
    @ApiModelProperty(value = "指示用户是锁定还是解锁。锁定的用户无法通过身份验证。返回值：true如果用户没有被锁定，false否则")
    @TableField("is_account_non_locked")
    private Boolean isAccountNonLocked;

    /** 指示用户的凭据（密码）是否已过期。过期的凭据会阻止身份验证。返回值：true如果用户的证书是有效的（即非到期），false如果不再有效（即到期 */
    @ApiModelProperty(value = "指示用户的凭据（密码）是否已过期。过期的凭据会阻止身份验证。返回值：true如果用户的证书是有效的（即非到期），false如果不再有效（即到期")
    @TableField("is_credentials_non_expired")
    private Boolean isCredentialsNonExpired;

    /** 指示用户是启用还是禁用。禁用的用户无法通过身份验证。返回值：true如果用户已启用，false否则 */
    @ApiModelProperty(value = "指示用户是启用还是禁用。禁用的用户无法通过身份验证。返回值：true如果用户已启用，false否则")
    @TableField("is_enabled")
    private Boolean isEnabled;


}
