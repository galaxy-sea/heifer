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
                Long tenantId = NumberUtils.parseNumber(request.getHeader(TENANT_ID), Long.class);
                JWTClaimsSet jwtClaimsSet = JwtUtil.parseAuthorization(authorization, jwtProperties.getKey());
                UserPrincipal userPrincipal = this.getUserPrincipal(jwtClaimsSet, tenantId);
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
     * @param tenantId 租户id 可能为null
     *
     * @return UserPrincipal
     */
    private UserPrincipal getUserPrincipal(JWTClaimsSet jwtClaimsSet, Long tenantId) {
        Long id = Long.valueOf(jwtClaimsSet.getJWTID());
        List<String> allPermission = userPrincipalService.listPermission(id, tenantId);
        List<SimpleGrantedAuthority> authorities = allPermission.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        UserPrincipal userPrincipal = new UserPrincipal();
        userPrincipal.setId(id);
        userPrincipal.setUsername(jwtClaimsSet.getSubject());
        userPrincipal.setAuthorities(authorities);

        return userPrincipal;
    }
}
