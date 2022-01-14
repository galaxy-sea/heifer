package plus.wcj.heifer.plugin.rbac.security;


import plus.wcj.heifer.common.security.config.SecurityAutoConfiguration;
import plus.wcj.heifer.common.security.filter.AuthenticationService;
import plus.wcj.heifer.matedata.properties.JwtProperties;
import plus.wcj.heifer.matedata.service.UserPrincipalService;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 * <p>
 * Security 配置
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2022-01-13
 */
@AutoConfigureBefore(SecurityAutoConfiguration.class)
@EnableWebSecurity
public class RbacSecurityAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(AuthenticationService.class)
    public AuthenticationService authenticationService(HandlerExceptionResolver handlerExceptionResolver, JwtProperties jwtProperties, UserPrincipalService userPrincipalService) {
        return new AuthenticationServiceImpl(handlerExceptionResolver, jwtProperties, userPrincipalService);
    }

}

