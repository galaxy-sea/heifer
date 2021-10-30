package plus.wcj.heifer.boot.common.security.dto;

import lombok.Data;

/**
 * <p>
 * 登录请求参数
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-10 15:52
 */
@Data
public class LoginRequest {

    /**
     * 用户名或邮箱或手机号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 记住我
     */
    private Boolean rememberMe = false;

}
