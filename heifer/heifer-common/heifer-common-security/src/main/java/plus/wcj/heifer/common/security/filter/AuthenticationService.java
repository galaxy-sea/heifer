package plus.wcj.heifer.common.security.filter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt序列化
 *
 * @author changjin wei(魏昌进)
 * @since 2021/12/21
 */
public interface AuthenticationService {

    void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain);
}
