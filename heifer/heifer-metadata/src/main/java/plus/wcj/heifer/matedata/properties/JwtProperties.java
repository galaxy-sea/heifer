package plus.wcj.heifer.matedata.properties;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;

/**
 * <p>
 * JWT 配置
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-07 13:42
 */
@Data
public class JwtProperties {
    /**
     * jwt 加密 key，默认值：xxxxxxxxxxxxxxx.
     */
    @Value("heifer.jwt.key?heifer.jwt.key:xxxxxxxxxxxxxxx")
    private String key;

    /**
     * jwt 过期时间，默认值：600000 {@code 10 分钟}.
     */
    @Value("heifer.jwt.ttl?heifer.jwt.ttl:600000L")
    private Long ttl;

    /**
     * 开启 记住我 之后 jwt 过期时间，默认值 604800000 {@code 7 天}
     */
    @Value("heifer.jwt.remember?heifer.jwt.remember:604800000L")
    private Long remember;
}
