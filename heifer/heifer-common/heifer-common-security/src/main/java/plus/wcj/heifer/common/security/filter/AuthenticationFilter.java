package plus.wcj.heifer.common.security.filter;


import lombok.extern.slf4j.Slf4j;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * Jwt 认证过滤器
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2022-01-13
 */
@Slf4j
public class AuthenticationFilter extends OncePerRequestFilter {

    private AuthenticationService authenticationService;


    public AuthenticationFilter(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        authenticationService.doFilterInternal(request, response, filterChain);
    }

}
