/*
 * Copyright 2021-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * Security ??????
 * </p>
 *
 * @author changjin wei(?????????)
 * @since 2022-01-13
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(IgnoreWebSecurityProperties.class)
@AutoConfigureOrder(AutoConfigureOrder.DEFAULT_ORDER - 1000)
public class SecurityAutoConfiguration extends WebSecurityConfigurerAdapter {
    private IgnoreWebSecurityProperties ignoreWebSecurityProperties;
    private ObjectProvider<List<IamOncePerRequestFilter>> iamOncePerRequestFilterObjectProviderLists;
    private ObjectProvider<RequestMappingHandlerMapping> requestMappingHandlerMappingObjectProvider;

    @Autowired
    public SecurityAutoConfiguration(IgnoreWebSecurityProperties ignoreWebSecurityProperties, ObjectProvider<List<IamOncePerRequestFilter>> iamOncePerRequestFilterObjectProviderLists, ObjectProvider<RequestMappingHandlerMapping> requestMappingHandlerMappingObjectProvider) {
        this.ignoreWebSecurityProperties = ignoreWebSecurityProperties;
        this.iamOncePerRequestFilterObjectProviderLists = iamOncePerRequestFilterObjectProviderLists;
        this.requestMappingHandlerMappingObjectProvider = requestMappingHandlerMappingObjectProvider;
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider impl = new DaoAuthenticationProvider();
        impl.setUserDetailsService(username -> {
            throw new ResultException(ResultStatusEnum.INTERNAL_SERVER_ERROR);
        });
        // TODO: 2021/6/6 changjin wei(?????????) ???????????????
        return impl;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // @formatter:off
        http.cors()
            // ?????? CSRF
            .and().csrf().disable()
            // ???????????????????????????????????? AuthController#login
            .formLogin().disable()
            .httpBasic().disable()

            // ????????????
            .authorizeRequests()
            // ?????????????????????????????????
            .anyRequest()
            .authenticated()
            // RBAC ?????? url ??????
            // .anyRequest()
            // .access("@rbacAuthorityService.hasPermission(request,authentication)")

            // ???????????????????????????????????? AuthController#logout
            .and().logout().disable()
            // Session ??????
            .sessionManagement()
            // ???????????????JWT????????????????????????Session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            // ????????????
            .and().exceptionHandling()
            .authenticationEntryPoint((request, response, authException) -> {
                throw new ResultException(ResultStatusEnum.UNAUTHORIZED);
            })
            .accessDeniedHandler((request, response, accessDeniedException) -> {
                throw new ResultException(ResultStatusEnum.FORBIDDEN);
            });
        // @formatter:on

        // ??????????????? JWT ?????????
        List<IamOncePerRequestFilter> filterList = iamOncePerRequestFilterObjectProviderLists.getIfAvailable();
        if (!CollectionUtils.isEmpty(filterList)) {
            for (IamOncePerRequestFilter iamOncePerRequestFilter : filterList) {
                http.addFilterBefore(iamOncePerRequestFilter, BasicAuthenticationFilter.class);
            }
        }
    }

    /**
     * ???????????????????????????????????????????????????????????? AuthController
     * ???????????? {@link #configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)} ?????????
     * {@code http.authorizeRequests().antMatchers("/api/auth/**").permitAll()}
     */
    @Override
    public void configure(WebSecurity web) {
        WebSecurity.IgnoredRequestConfigurer ignoring = web.ignoring();
        //noinspection ConstantConditions
        this.ignoreAnnotation(ignoring, this.requestMappingHandlerMappingObjectProvider.getIfAvailable());
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

