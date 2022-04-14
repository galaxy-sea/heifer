package plus.wcj.heifer.metadata.properties;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;

/**
 * <p>
 * JWT 配置
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2022-01-13
 */
@Data
public class JwtProperties {
    /**
     * jwt 加密 key，默认值：xxxxxxxxxxxxxxx.
     */
    @Value(value = "${heifer.jwt.key?heifer.jwt.key:xxxxxxxxxxxxxxx}")
    private String key;

    /**
     * jwt 过期时间，默认值：600000 {@code 10 分钟}.
     */
    @Value(value = "${heifer.jwt.ttl?heifer.jwt.ttl:600000}")
    private Long ttl;

    /**
     * 开启 记住我 之后 jwt 过期时间，默认值 604800000 {@code 7 天}
     */
    @Value(value = "${heifer.jwt.remember?heifer.jwt.remember:604800000}")
    private Long remember;
}
