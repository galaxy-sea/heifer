package plus.wcj.heifer.plugin.rbac.pojo.vo;

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
public class JwtVo {
    /**
     * token 字段
     */
    private String token;
    /**
     * token类型
     */
    private String tokenType = "Bearer ";

    public JwtVo(String token) {
        this.token = this.tokenType + token;
    }
}
