package plus.wcj.heifer.plugin.rbac.security;


import plus.wcj.heifer.common.security.config.SecurityAutoConfiguration;
import plus.wcj.heifer.common.security.filter.AuthenticationService;
import plus.wcj.heifer.metadata.properties.JwtProperties;
import plus.wcj.heifer.metadata.tenant.UserPrincipalService;
import plus.wcj.heifer.plugin.rbac.security.support.SecurityUserHandlerMethodArgumentResolver;
import plus.wcj.heifer.plugin.rbac.security.support.TenantHandlerMethodArgumentResolver;

import lombok.AllArgsConstructor;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

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
@AllArgsConstructor
public class RbacSecurityAutoConfiguration implements WebMvcConfigurer {

    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtProperties jwtProperties;
    private final UserPrincipalService userPrincipalService;


    @Bean
    @ConditionalOnMissingBean(AuthenticationService.class)
    public AuthenticationService authenticationService() {
        return new AuthenticationServiceImpl(handlerExceptionResolver, jwtProperties, userPrincipalService);
    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(0, new SecurityUserHandlerMethodArgumentResolver());
        resolvers.add(0, new TenantHandlerMethodArgumentResolver(userPrincipalService));
    }
}

