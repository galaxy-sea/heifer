package plus.wcj.heifer.plugin.rbac.pojo.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * JWT 响应返回
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2022-01-13
 */
@Getter
@Setter
public class JwtDto {
    /**
     * token 字段
     */
    private String token;
    /**
     * token类型
     */
    private String tokenType = "Bearer ";

    public JwtDto(String token) {
        this.token = this.tokenType + token;
    }
}
