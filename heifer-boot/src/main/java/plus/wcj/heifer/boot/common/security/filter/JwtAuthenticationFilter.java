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

    private static final String DATA401 = "{\"code\":\"" + ResultStatus.UNAUTHORIZED.getCode() + "\",\"message\":\"" + ResultStatus.UNAUTHORIZED.getMessage() + "\"}";
    private static final String DATA403 = "{\"code\":\"" + ResultStatus.FORBIDDEN.getCode() + "\",\"message\":\"" + ResultStatus.FORBIDDEN.getMessage() + "\"}";
    private static final String DATA500 = "{\"code\":\"" + ResultStatus.INTERNAL_SERVER_ERROR.getCode() + "\",\"message\":\"" + ResultStatus.INTERNAL_SERVER_ERROR.getMessage() + "\"}";


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil, ObjectMapper objectMapper) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.objectMapper = objectMapper;
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
            this.print(response, e);
        }
    }


    private void print(HttpServletResponse response, Exception ex) throws JsonProcessingException {

        String result;
        if (ex instanceof ResultException) {
            ResultStatus resultStatus = ((ResultException) ex).getResultStatus();
            if (resultStatus == ResultStatus.UNAUTHORIZED) {
                result = DATA401;
            } else if (resultStatus == ResultStatus.FORBIDDEN) {
                result = DATA403;
            } else {
                Result<Object> fail = Result.fail(resultStatus);
                result = this.objectMapper.writeValueAsString(fail);
            }
            response.setStatus(resultStatus.getHttpStatus());
        } else {
            result = DATA500;
            response.setStatus(ResultStatus.INTERNAL_SERVER_ERROR.getHttpStatus());
        }

        try (ServletOutputStream outputStream = response.getOutputStream()) {
            outputStream.print(result);
            outputStream.flush();
        } catch (IOException e) {
            log.error(ex.getMessage());
            if (log.isDebugEnabled()) {
                log.error("小可爱，注意检查代码哦", ex);
            }
        }
    }
}
