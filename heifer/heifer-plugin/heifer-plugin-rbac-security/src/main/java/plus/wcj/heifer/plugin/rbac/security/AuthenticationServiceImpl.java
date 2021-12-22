package plus.wcj.heifer.plugin.rbac.security;

import plus.wcj.heifer.common.security.filter.AuthenticationService;

import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author changjin wei(魏昌进)
 * @since 2021/12/21
 */
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JwtUtil jwtUtil;
    private final HandlerExceptionResolver handlerExceptionResolver;

    public AuthenticationServiceImpl(JwtUtil jwtUtil, HandlerExceptionResolver handlerExceptionResolver) {
        this.jwtUtil = jwtUtil;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {

            String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (StringUtils.hasLength(authorization)) {
                RbacAccountDto account = this.jwtUtil.getAccount(authorization);
                String tenantId = getAccountMetadata(request, TENANT_ID);
                UserPrincipal userPrincipal = this.getUserPrincipal(account, tenantId);
                this.setSecurityContext(userPrincipal, request);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        }
    }
}
