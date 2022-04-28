package plus.wcj.heifer.plugin.iam.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 登录请求参数
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2022-01-13
 */
@Data
public class LoginDto {

    /**
     * 用户名或邮箱或手机号
     */
    @NotEmpty
    private String phone;

    /**
     * 密码
     */
    @NotEmpty
    private String password;

    /**
     * 记住我
     */
    private Boolean rememberMe;

}
