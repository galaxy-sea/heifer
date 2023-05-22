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

package plus.wcj.heifer.plugin.iam.security;


import plus.wcj.heifer.common.security.config.SecurityAutoConfiguration;
import plus.wcj.heifer.common.security.filter.IamOncePerRequestFilter;
import plus.wcj.heifer.metadata.properties.JwtProperties;
import plus.wcj.heifer.metadata.iam.UserPrincipalService;
import plus.wcj.heifer.plugin.iam.security.support.mvc.ChaosAuthenticationFilter;
import plus.wcj.heifer.plugin.iam.security.support.mvc.JwtAuthenticationFilter;
import plus.wcj.heifer.plugin.iam.security.support.mvc.SecurityUserHandlerMethodArgumentResolver;
import plus.wcj.heifer.plugin.iam.security.support.mvc.UserHandlerMethodArgumentResolver;
import plus.wcj.heifer.plugin.iam.security.support.registry.ChaosMetadata;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
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
public class IamWebMvcAutoConfiguration implements WebMvcConfigurer {

    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtProperties jwtProperties;
    private final UserPrincipalService userPrincipalService;

    private final ObjectProvider<List<UserPrincipalCustomizeService>> userPrincipalCustomizeServiceLists;

    public IamWebMvcAutoConfiguration(HandlerExceptionResolver handlerExceptionResolver, JwtProperties jwtProperties,
                                      UserPrincipalService userPrincipalService,
                                      ObjectProvider<List<UserPrincipalCustomizeService>> userPrincipalCustomizeServiceLists) {
        this.handlerExceptionResolver = handlerExceptionResolver;
        this.jwtProperties = jwtProperties;
        this.userPrincipalService = userPrincipalService;
        this.userPrincipalCustomizeServiceLists = userPrincipalCustomizeServiceLists;
    }

    @Bean
    @ConditionalOnMissingBean
    public IamOncePerRequestFilter iamOncePerRequestFilter() {
        return new JwtAuthenticationFilter(handlerExceptionResolver,
                                           jwtProperties,
                                           userPrincipalService,
                                           userPrincipalCustomizeServiceLists.getIfAvailable(ArrayList::new)
        );
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnBean(ChaosMetadata.class)
    public ChaosAuthenticationFilter chaosAuthenticationFilter(ChaosMetadata chaosMetadata) {
        return new ChaosAuthenticationFilter(handlerExceptionResolver, chaosMetadata);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(0, new SecurityUserHandlerMethodArgumentResolver());
        resolvers.add(0, new UserHandlerMethodArgumentResolver(userPrincipalService));
    }
}

