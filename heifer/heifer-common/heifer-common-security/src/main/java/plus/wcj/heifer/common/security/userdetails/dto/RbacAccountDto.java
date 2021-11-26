package plus.wcj.heifer.common.security.userdetails.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 账户表
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RbacAccountDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 账户名 */

    private String username;

    /** 手机号 */

    private String phone;

    /** 邮箱 */

    private String email;

    /** 密码 */

    private String password;

    /** 昵称 */

    private String nickname;

    /** 指示账户的帐户是否已过期。过期的帐户无法通过身份验证。返回值：true如果账户的帐户是否有效（即未过期），false如果不再有效（即到期） */

    private boolean isAccountNonExpired;

    /** 指示账户是锁定还是解锁。锁定的账户无法通过身份验证。返回值：true如果账户没有被锁定，false否则 */

    private boolean isAccountNonLocked;

    /** 指示账户的凭据（密码）是否已过期。过期的凭据会阻止身份验证。返回值：true如果账户的证书是有效的（即非到期），false如果不再有效（即到期 */

    private boolean isCredentialsNonExpired;

    /** 指示账户是启用还是禁用。禁用的账户无法通过身份验证。返回值：true如果账户已启用，false否则 */

    private boolean isEnabled;


    private RbacAccountManageDto accountManage;


}
