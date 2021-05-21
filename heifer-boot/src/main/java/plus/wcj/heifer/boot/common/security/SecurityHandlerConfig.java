package plus.wcj.heifer.boot.common.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.access.AccessDeniedHandler;
import plus.wcj.heifer.boot.common.exception.ResultException;
import plus.wcj.heifer.boot.common.exception.ResultStatus;

/**
 * <p>
 * Security 结果处理配置
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-07 17:31
 */
@Configuration
public class SecurityHandlerConfig {

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            throw new ResultException(ResultStatus.INTERNAL_SERVER_ERROR);
        };
    }

}
