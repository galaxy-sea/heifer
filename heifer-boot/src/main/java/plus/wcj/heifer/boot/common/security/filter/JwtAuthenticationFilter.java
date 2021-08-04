package plus.wcj.heifer.boot.common.security.filter;


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
import plus.wcj.heifer.boot.common.exception.ResultException;
import plus.wcj.heifer.boot.common.exception.ResultStatus;
import plus.wcj.heifer.boot.common.mvc.result.Result;
import plus.wcj.heifer.boot.common.security.jwt.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil, ObjectMapper objectMapper) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
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
            this.print(response, e);
        }
    }


    private void print(HttpServletResponse response, Exception ex) {
        Result<?> result;

        if (ex instanceof ResultException) {
            ResultStatus resultStatus = ((ResultException) ex).getResultStatus();
            result = Result.fail(resultStatus);
            response.setStatus(resultStatus.getHttpStatus());
        } else {
            result = Result.fail(ResultStatus.INTERNAL_SERVER_ERROR);
            response.setStatus(ResultStatus.INTERNAL_SERVER_ERROR.getHttpStatus());
        }

        try (ServletOutputStream outputStream = response.getOutputStream()) {
            String s = objectMapper.writeValueAsString(result);
            outputStream.print(s);
            outputStream.flush();
        } catch (IOException e) {
            log.error(ex.getMessage());
            if (log.isDebugEnabled()) {
                log.error("小可爱，注意检查代码哦", ex);
            }
        }
    }
}
