package plus.wcj.heifer.boot.common.security.filter;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.StringUtils;
import plus.wcj.heifer.boot.common.security.jwt.JwtUtil;
import plus.wcj.heifer.boot.common.security.userdetails.HeiferUserDetailsServiceImpl;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacAccountDto;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacAccountManageDto;
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

    public static final String TENANT_ID = "Tenant-Id";


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil, HeiferUserDetailsServiceImpl heiferUserDetailsService, HandlerExceptionResolver handlerExceptionResolver) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.heiferUserDetailsService = heiferUserDetailsService;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws JsonProcessingException {
        try {
            String authorization = getAccountMetadata(request, HttpHeaders.AUTHORIZATION);
            if (StringUtils.isNotBlank(authorization)) {
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

    /**
     *  保存用户信息 到 Spring Security
     *
     * @param userPrincipal
     * @param request
     */
    private void setSecurityContext(UserPrincipal userPrincipal, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * @param account 账户
     * @param tenantIdString 租户信息
     *
     * @return 返回Spring Security的user继承累
     */
    private UserPrincipal getUserPrincipal(RbacAccountDto account, String tenantIdString) {
        List<String> allPermission = null;
        RbacAccountManageDto rbacAccountManageDto = null;
        if (StringUtils.isNotBlank(tenantIdString)) {
            Long tenantId = Long.valueOf(tenantIdString);
            rbacAccountManageDto = heiferUserDetailsService.loadAccountManage(tenantId, account.getId());
            allPermission = heiferUserDetailsService.getAllPermission(tenantId, account.getId());
        }
        return UserPrincipal.create(account, rbacAccountManageDto, allPermission);
    }


    /**
     * 获取账户的元数据，根据 headerName 获取 httpHeader 和 httpParameter 中获取
     *
     * @param request request
     * @param headerName headerName
     *
     * @return value
     */
    private String getAccountMetadata(HttpServletRequest request, String headerName) {
        String value = request.getHeader(headerName);
        if (StringUtils.isNotBlank(value)) {
            return value;
        }
        value = request.getParameter(headerName);
        if (StringUtils.isNotBlank(value)) {
            return value;
        }
        return null;
    }
}
