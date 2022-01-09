package plus.wcj.heifer.plugin.rbac.pojo.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 登录请求参数
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-10 15:52
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
