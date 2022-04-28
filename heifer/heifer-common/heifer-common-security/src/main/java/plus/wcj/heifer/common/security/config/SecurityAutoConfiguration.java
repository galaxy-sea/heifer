package plus.wcj.heifer.common.security.config;


import plus.wcj.heifer.common.security.filter.IamOncePerRequestFilter;
import plus.wcj.heifer.common.security.properties.IgnoreWebSecurityProperties;
import plus.wcj.heifer.metadata.annotation.IgnoreWebSecurity;
import plus.wcj.heifer.metadata.exception.ResultException;
import plus.wcj.heifer.metadata.exception.ResultStatusEnum;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;
import java.util.Set;

/**
 * <p>
 * Security 配置
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2022-01-13
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(IgnoreWebSecurityProperties.class)
@AutoConfigureOrder(AutoConfigureOrder.DEFAULT_ORDER - 1000)
public class SecurityAutoConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private IgnoreWebSecurityProperties ignoreWebSecurityProperties;
    @Autowired
    private ObjectProvider<IamOncePerRequestFilter> iamOncePerRequestFilterObjectProvider;
    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider impl = new DaoAuthenticationProvider();
        impl.setUserDetailsService(username -> {
            throw new ResultException(ResultStatusEnum.INTERNAL_SERVER_ERROR);
        });
        // TODO: 2021/6/6 changjin wei(魏昌进) 需要缓存啊
        return impl;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // @formatter:off
        http.cors()
            // 关闭 CSRF
            .and().csrf().disable()
            // 登录行为由自己实现，参考 AuthController#login
            .formLogin().disable()
            .httpBasic().disable()

            // 认证请求
            .authorizeRequests()
            // 所有请求都需要登录访问
            .anyRequest()
            .authenticated()
            // RBAC 动态 url 认证
            // .anyRequest()
            // .access("@rbacAuthorityService.hasPermission(request,authentication)")

            // 登出行为由自己实现，参考 AuthController#logout
            .and().logout().disable()
            // Session 管理
            .sessionManagement()
            // 因为使用了JWT，所以这里不管理Session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            // 异常处理
            .and().exceptionHandling();
        // .authenticationEntryPoint((request, response, authException) -> {
        //     throw new ResultException(ResultStatusEnum.UNAUTHORIZED);
        // })
        // .accessDeniedHandler((request, response, accessDeniedException) -> {
        //     throw new ResultException(ResultStatusEnum.FORBIDDEN);
        // });
        // @formatter:on

        // 添加自定义 JWT 过滤器
        http.addFilterBefore(iamOncePerRequestFilterObjectProvider.getIfAvailable(), BasicAuthenticationFilter.class);
    }

    /**
     * 放行所有不需要登录就可以访问的请求，参见 AuthController
     * 也可以在 {@link #configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)} 中配置
     * {@code http.authorizeRequests().antMatchers("/api/auth/**").permitAll()}
     */
    @Override
    public void configure(WebSecurity web) {
        WebSecurity.IgnoredRequestConfigurer ignoring = web.ignoring();
        this.ignoreAnnotation(ignoring, this.requestMappingHandlerMapping);
        this.ignoreProperties(ignoring, this.ignoreWebSecurityProperties);
    }

    private void ignoreAnnotation(WebSecurity.IgnoredRequestConfigurer ignoring, RequestMappingHandlerMapping requestMappingHandlerMapping) {
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
            HandlerMethod handlerMethod = entry.getValue();
            if (handlerMethod.hasMethodAnnotation(IgnoreWebSecurity.class)) {
                Set<String> patternValues = entry.getKey().getPatternValues();
                Set<RequestMethod> methods = entry.getKey().getMethodsCondition().getMethods();
                if (CollectionUtils.isEmpty(methods)) {
                    ignoring.antMatchers(patternValues.toArray(new String[0]));
                } else {
                    for (RequestMethod method : methods) {
                        ignoring.antMatchers(HttpMethod.resolve(method.name()), patternValues.toArray(new String[0]));
                    }
                }
            }
        }
    }

    private void ignoreProperties(WebSecurity.IgnoredRequestConfigurer ignoring, IgnoreWebSecurityProperties ignoreWebSecurityProperties) {
        ignoring.antMatchers(HttpMethod.GET, ignoreWebSecurityProperties.getGet())
                .antMatchers(HttpMethod.POST, ignoreWebSecurityProperties.getPost())
                .antMatchers(HttpMethod.DELETE, ignoreWebSecurityProperties.getDelete())
                .antMatchers(HttpMethod.PUT, ignoreWebSecurityProperties.getPut())
                .antMatchers(HttpMethod.HEAD, ignoreWebSecurityProperties.getHead())
                .antMatchers(HttpMethod.PATCH, ignoreWebSecurityProperties.getPatch())
                .antMatchers(HttpMethod.OPTIONS, ignoreWebSecurityProperties.getOptions())
                .antMatchers(HttpMethod.TRACE, ignoreWebSecurityProperties.getTrace())
                .antMatchers(ignoreWebSecurityProperties.getPattern());
    }
}

