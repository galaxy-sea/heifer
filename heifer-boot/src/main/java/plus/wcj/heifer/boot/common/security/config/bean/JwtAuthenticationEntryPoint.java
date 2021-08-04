package plus.wcj.heifer.boot.common.security.config.bean;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import plus.wcj.heifer.boot.common.exception.ResultException;
import plus.wcj.heifer.boot.common.exception.ResultStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/6/11
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        throw new ResultException(ResultStatus.UNAUTHORIZED);
    }
}
