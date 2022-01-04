package plus.wcj.heifer.plugin.rbac.security;


import plus.wcj.heifer.common.security.config.SecurityAutoConfiguration;
import plus.wcj.heifer.common.security.filter.AuthenticationService;
import plus.wcj.heifer.matedata.properties.JwtProperties;
import plus.wcj.heifer.plugin.rbac.service.account.RbacAccountService;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.HandlerExceptionResolver;

/**
 * <p>
 * Security 配置
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-07 16:46
 */
@AutoConfigureBefore(SecurityAutoConfiguration.class)
public class RbacSecurityAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(AuthenticationService.class)
    public AuthenticationService authenticationService(HandlerExceptionResolver handlerExceptionResolver, JwtProperties jwtProperties, RbacAccountService rbacAccountService) {
        return new AuthenticationServiceImpl(handlerExceptionResolver, jwtProperties, rbacAccountService);
    }

    @Bean
    @ConditionalOnMissingBean(AuthService.class)
    public AuthService authService(PasswordEncoder passwordEncoder, RbacAccountService rbacAccountService, JwtProperties jwtProperties) {
        return new AuthServiceImpl(passwordEncoder, rbacAccountService, jwtProperties);
    }
    //
    // @Bean
    // public AuthController authController(AuthService authService) {
    //     return new AuthController(authService);
    // }
}

