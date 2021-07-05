package plus.wcj.heifer.boot.common.security.jwt;

import java.util.Map;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/7/5
 */
public interface JwtClaim<T> {

    Map<String, String> serialize();

    T deserialization(Map<String, Object> map);
}
