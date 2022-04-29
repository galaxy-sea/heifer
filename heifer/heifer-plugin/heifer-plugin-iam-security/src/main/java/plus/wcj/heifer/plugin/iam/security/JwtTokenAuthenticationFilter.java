package plus.wcj.heifer.plugin.iam.security;

import com.nimbusds.jwt.JWTClaimsSet;
import plus.wcj.heifer.common.security.UserPrincipal;
import plus.wcj.heifer.common.security.filter.IamOncePerRequestFilter;
import plus.wcj.heifer.metadata.properties.JwtProperties;
import plus.wcj.heifer.metadata.tenant.UserPrincipalService;
import plus.wcj.heifer.tools.utils.JwtUtil;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/12/21
 */
public class JwtTokenAuthenticationFilter extends IamOncePerRequestFilter {
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtProperties jwtProperties;
    private final UserPrincipalService userPrincipalService;

    public static final String TENANT_ID = "Tenant-Id";


    public JwtTokenAuthenticationFilter(HandlerExceptionResolver handlerExceptionResolver, JwtProperties jwtProperties, UserPrincipalService userPrincipalService) {
        this.handlerExceptionResolver = handlerExceptionResolver;
        this.jwtProperties = jwtProperties;
        this.userPrincipalService = userPrincipalService;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {

            String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (StringUtils.hasLength(authorization)) {
                JWTClaimsSet jwtClaimsSet = JwtUtil.parseAuthorization(authorization, jwtProperties.getKey());
                UserPrincipal userPrincipal = this.getUserPrincipal(jwtClaimsSet, request.getHeader(TENANT_ID));
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        }
    }


    /**
     * 获取{@link UserPrincipal}
     *
     * @param jwtClaimsSet JSON Web Token (JWT) claims set.
     * @param headerTenantId 租户id 可以为null
     *
     * @return UserPrincipal
     */
    private UserPrincipal getUserPrincipal(JWTClaimsSet jwtClaimsSet, String headerTenantId) {
        Long accountId = Long.valueOf(jwtClaimsSet.getJWTID());
        UserPrincipal userPrincipal = new UserPrincipal();
        userPrincipal.setId(accountId);
        userPrincipal.setUsername(jwtClaimsSet.getSubject());

        if (StringUtils.hasText(headerTenantId)) {
            Long tenantId = NumberUtils.parseNumber(headerTenantId, Long.class);
            List<String> allPermission = userPrincipalService.listPermission(accountId, tenantId);
            List<SimpleGrantedAuthority> authorities = allPermission.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            userPrincipal.setAuthorities(authorities);
            userPrincipal.setTenantId(tenantId);
        }

        return userPrincipal;
    }
}
