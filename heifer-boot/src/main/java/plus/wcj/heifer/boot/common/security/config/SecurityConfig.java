package plus.wcj.heifer.boot.common.security.config;

import lombok.AllArgsConstructor;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import plus.wcj.heifer.boot.common.exception.ResultException;
import plus.wcj.heifer.boot.common.exception.ResultStatus;
import plus.wcj.heifer.boot.common.security.config.bean.JwtAuthenticationEntryPoint;
import plus.wcj.heifer.boot.common.security.filter.JwtAuthenticationFilter;
import plus.wcj.heifer.boot.common.security.properties.IgnoreProperties;
import plus.wcj.heifer.boot.common.security.userdetails.HeiferUserDetailsServiceImpl;

import java.util.HashMap;
import java.util.Map;

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
@AllArgsConstructor
@EnableConfigurationProperties(IgnoreProperties.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final IgnoreProperties ignoreProperties;

    private final HeiferUserDetailsServiceImpl heiferUserDetailsServiceImpl;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    @SuppressWarnings("AlibabaRemoveCommentedCode")
    public PasswordEncoder encoder() {
        // DelegatingPasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String encodingId = "bcrypt";
        Map<String, PasswordEncoder> encoders = new HashMap<>();

        encoders.put(encodingId, new BCryptPasswordEncoder());

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
        return new DelegatingPasswordEncoder(encodingId, encoders);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    /*
    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.authenticationProvider(daoAuthenticationProvider()).userDetailsService(customUserDetailsService).passwordEncoder(encoder());
    // }
    */

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider impl = new DaoAuthenticationProvider();
        impl.setPasswordEncoder(encoder());
        impl.setUserDetailsService(heiferUserDetailsServiceImpl);

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
            .and().exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler((request, response, accessDeniedException) -> {
                throw new ResultException(ResultStatus.FORBIDDEN);
            });
        // @formatter:on

        // 添加自定义 JWT 过滤器
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 放行所有不需要登录就可以访问的请求，参见 AuthController
     * 也可以在 {@link #configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)} 中配置
     * {@code http.authorizeRequests().antMatchers("/api/auth/**").permitAll()}
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
           .antMatchers(HttpMethod.GET, ignoreProperties.getGet())
           .antMatchers(HttpMethod.POST, ignoreProperties.getPost())
           .antMatchers(HttpMethod.DELETE, ignoreProperties.getDelete())
           .antMatchers(HttpMethod.PUT, ignoreProperties.getPut())
           .antMatchers(HttpMethod.HEAD, ignoreProperties.getHead())
           .antMatchers(HttpMethod.PATCH, ignoreProperties.getPatch())
           .antMatchers(HttpMethod.OPTIONS, ignoreProperties.getOptions())
           .antMatchers(HttpMethod.TRACE, ignoreProperties.getTrace())
           .antMatchers(ignoreProperties.getPattern());
    }
}
