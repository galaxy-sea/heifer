package plus.wcj.heifer.boot.common.security.filter;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.StringUtils;
import plus.wcj.heifer.boot.common.security.jwt.JwtUtil;
import plus.wcj.heifer.boot.common.security.userdetails.HeiferUserDetailsServiceImpl;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacAccountDto;
import plus.wcj.heifer.boot.common.security.userdetails.dto.UserPrincipal;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * Jwt 认证过滤器
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-10 15:15
 */
@Slf4j
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private final JwtUtil jwtUtil;
    private final HeiferUserDetailsServiceImpl heiferUserDetailsService;
    private final HandlerExceptionResolver handlerExceptionResolver;


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil, HeiferUserDetailsServiceImpl heiferUserDetailsService, HandlerExceptionResolver handlerExceptionResolver) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.heiferUserDetailsService = heiferUserDetailsService;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws JsonProcessingException {
        try {
            String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (StringUtils.isNotBlank(authorization)) {
                RbacAccountDto account = this.jwtUtil.getAccount(authorization);
                List<String> allPermission = heiferUserDetailsService.getAllPermission(account.getAccountManage().getRbacTenantId(), account.getId());
                UserPrincipal userPrincipal = UserPrincipal.create(account, allPermission);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        }

    }
}
