package plus.wcj.heifer.boot.common.security.config;

import plus.wcj.heifer.boot.common.exception.ResultException;
import plus.wcj.heifer.boot.common.exception.ResultStatusEnum;
import plus.wcj.heifer.boot.common.security.filter.JwtAuthenticationFilter;
import plus.wcj.heifer.boot.common.security.jwt.JwtUtil;
import plus.wcj.heifer.boot.common.security.properties.IgnoreProperties;
import plus.wcj.heifer.boot.common.security.properties.IgnoreWebSecurity;
import plus.wcj.heifer.boot.common.security.userdetails.HeiferUserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * Security 配置
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-07 16:46
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableConfigurationProperties(IgnoreProperties.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final IgnoreProperties ignoreProperties;
    private final HeiferUserDetailsServiceImpl heiferUserDetailsServiceImpl;
    private final JwtUtil jwtUtil;
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // @formatter:off
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
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
            .and().exceptionHandling()
            .authenticationEntryPoint((request, response, authException) -> {
                throw new ResultException(ResultStatusEnum.UNAUTHORIZED);
            })
            .accessDeniedHandler((request, response, accessDeniedException) -> {
                throw new ResultException(ResultStatusEnum.FORBIDDEN);
            });
        // @formatter:on

        // 添加自定义 JWT 过滤器
        http.addFilter(new JwtAuthenticationFilter(this.authenticationManager(), this.jwtUtil, this.heiferUserDetailsServiceImpl, handlerExceptionResolver));
    }

    /**
     * 放行所有不需要登录就可以访问的请求，参见 AuthController
     * 也可以在 {@link #configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)} 中配置
     * {@code http.authorizeRequests().antMatchers("/api/auth/**").permitAll()}
     */
    @Override
    public void configure(WebSecurity web) {
        WebSecurity.IgnoredRequestConfigurer ignoring = web.ignoring();

        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
            if (entry.getValue().hasMethodAnnotation(IgnoreWebSecurity.class)) {
                RequestMappingInfo key = entry.getKey();
                Set<String> patternValues = key.getPatternValues();
                Set<RequestMethod> methods = key.getMethodsCondition().getMethods();
                if (CollectionUtils.isEmpty(methods)) {
                    ignoring.antMatchers(patternValues.toArray(new String[0]));
                } else {
                    for (RequestMethod method : methods) {
                        ignoring.antMatchers(HttpMethod.resolve(method.name()), patternValues.toArray(new String[0]));
                    }
                }


            }
        }


        ignoring.antMatchers(HttpMethod.GET, this.ignoreProperties.getGet())
                .antMatchers(HttpMethod.POST, this.ignoreProperties.getPost())
                .antMatchers(HttpMethod.DELETE, this.ignoreProperties.getDelete())
                .antMatchers(HttpMethod.PUT, this.ignoreProperties.getPut())
                .antMatchers(HttpMethod.HEAD, this.ignoreProperties.getHead())
                .antMatchers(HttpMethod.PATCH, this.ignoreProperties.getPatch())
                .antMatchers(HttpMethod.OPTIONS, this.ignoreProperties.getOptions())
                .antMatchers(HttpMethod.TRACE, this.ignoreProperties.getTrace())
                .antMatchers(this.ignoreProperties.getPattern());
    }
}

@Configuration
class BeanConfig {
    @Bean
    @SuppressWarnings({"AlibabaRemoveCommentedCode", "CommentedOutCode"})
    public PasswordEncoder encoder() {
        // DelegatingPasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encodingId = "bcrypt";
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        Map<String, PasswordEncoder> encoders = new HashMap<>(8);

        encoders.put(encodingId, bCryptPasswordEncoder);

        // encoders.put("ldap", new org.springframework.security.crypto.password.LdapShaPasswordEncoder());
        // encoders.put("MD4", new org.springframework.security.crypto.password.Md4PasswordEncoder());
        // encoders.put("MD5", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("MD5"));
        // encoders.put("noop", org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance());
        // encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        // encoders.put("scrypt", new SCryptPasswordEncoder());
        // encoders.put("SHA-1", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-1"));
        // encoders.put("SHA-256", new org.springframework.security.crypto.password.MessageDigestPasswordEncoder("SHA-256"));
        // encoders.put("sha256", new org.springframework.security.crypto.password.StandardPasswordEncoder());
        // encoders.put("argon2", new Argon2PasswordEncoder());

        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder(encodingId, encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(bCryptPasswordEncoder);

        return passwordEncoder;
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider impl = new DaoAuthenticationProvider();
        impl.setPasswordEncoder(passwordEncoder);
        impl.setUserDetailsService(username -> {
            throw new ResultException(ResultStatusEnum.INTERNAL_SERVER_ERROR);
        });

        // TODO: 2021/6/6 changjin wei(魏昌进) 需要缓存啊

        return impl;
    }
}
