package plus.wcj.heifer.boot.common.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import plus.wcj.heifer.boot.common.exception.ResultException;
import plus.wcj.heifer.boot.common.exception.ResultStatus;
import plus.wcj.heifer.boot.common.security.config.bean.JwtAuthenticationEntryPoint;
import plus.wcj.heifer.boot.common.security.filter.JwtAuthenticationFilter;
import plus.wcj.heifer.boot.common.security.properties.IgnoreProperties;
import plus.wcj.heifer.boot.common.security.userdetails.CustomUserDetailsServiceImpl;

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
@EnableConfigurationProperties(IgnoreProperties.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private IgnoreProperties ignoreProperties;

    @Autowired
    private CustomUserDetailsServiceImpl customUserDetailsServiceImpl;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
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
        impl.setUserDetailsService(customUserDetailsServiceImpl);

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
