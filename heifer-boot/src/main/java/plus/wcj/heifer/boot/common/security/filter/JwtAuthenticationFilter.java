package plus.wcj.heifer.boot.common.security.filter;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import plus.wcj.heifer.boot.common.exception.ResultStatus;
import plus.wcj.heifer.boot.common.security.jwt.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    private final ObjectMapper objectMapper;
    private final HandlerExceptionResolver handlerExceptionResolver;

    private static final String DATA401 = "{\"code\":\"" + ResultStatus.UNAUTHORIZED.getCode() + "\",\"message\":\"" + ResultStatus.UNAUTHORIZED.getMessage() + "\"}";
    private static final String DATA403 = "{\"code\":\"" + ResultStatus.FORBIDDEN.getCode() + "\",\"message\":\"" + ResultStatus.FORBIDDEN.getMessage() + "\"}";
    private static final String DATA500 = "{\"code\":\"" + ResultStatus.INTERNAL_SERVER_ERROR.getCode() + "\",\"message\":\"" + ResultStatus.INTERNAL_SERVER_ERROR.getMessage() + "\"}";


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil, ObjectMapper objectMapper, HandlerExceptionResolver handlerExceptionResolver) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.objectMapper = objectMapper;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws JsonProcessingException {
        try {
            String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (StringUtils.isNotBlank(authorization)) {

                UserDetails userDetails = this.jwtUtil.getUserPrincipal(authorization);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        }
    }
}
