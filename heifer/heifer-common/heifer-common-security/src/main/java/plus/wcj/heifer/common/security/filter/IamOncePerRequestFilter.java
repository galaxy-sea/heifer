package plus.wcj.heifer.common.security.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/04/29
 */
public abstract class IamOncePerRequestFilter extends OncePerRequestFilter {

    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {

    }

}
