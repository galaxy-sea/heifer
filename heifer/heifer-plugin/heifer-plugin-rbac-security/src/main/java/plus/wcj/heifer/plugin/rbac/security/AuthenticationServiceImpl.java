package plus.wcj.heifer.plugin.rbac.security;

import plus.wcj.heifer.common.security.UserPrincipal;
import plus.wcj.heifer.common.security.filter.AuthenticationService;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/12/21
 */
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JwtUtil jwtUtil;
    private final HandlerExceptionResolver handlerExceptionResolver;

    public static final String TENANT_ID = "Tenant-Id";


    public AuthenticationServiceImpl(JwtUtil jwtUtil, HandlerExceptionResolver handlerExceptionResolver) {
        this.jwtUtil = jwtUtil;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {

            String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (StringUtils.hasLength(authorization)) {
                Long tenantId = NumberUtils.parseNumber(request.getHeader(TENANT_ID), Long.class);
                UserPrincipal userPrincipal = this.jwtUtil.parseAuthorization(authorization, tenantId);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        }
    }
}
