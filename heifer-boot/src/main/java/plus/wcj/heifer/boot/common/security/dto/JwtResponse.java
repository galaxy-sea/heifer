package plus.wcj.heifer.boot.common.security.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * JWT 响应返回
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-10 16:01
 */
@Getter
@Setter
public class JwtResponse {
    /**
     * token 字段
     */
    private String token;
    /**
     * token类型
     */
    private String tokenType = "Bearer ";

    public JwtResponse(String token) {
        this.token = this.tokenType + token;
    }
}
