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

package plus.wcj.heifer.common.swagger.configuration;

import org.springdoc.core.customizers.ParameterCustomizer;
import org.springdoc.core.discoverer.SpringDocParameterNameDiscoverer;
import org.springdoc.core.service.GenericParameterService;
import org.springdoc.core.service.OperationService;
import org.springdoc.core.service.RequestBodyService;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import plus.wcj.heifer.common.swagger.SecurityOperationCustomizer;
import plus.wcj.heifer.common.swagger.RequestService;
import plus.wcj.heifer.common.swagger.ResponseBodyResultOperationCustomizer;
import plus.wcj.heifer.common.swagger.SecurityAnnotationOperationCustomizer;
import plus.wcj.heifer.common.swagger.customizers.FieldToIgnoreOperationCustomizer;

import java.util.List;
import java.util.Optional;


/**
 * @author changjin wei(魏昌进)
 * @since 2022/6/20
 */
@Configuration
@PropertySource("classpath:application-swagger.properties")
@AutoConfigureBefore(com.github.xiaoymin.knife4j.spring.configuration.Knife4jAutoConfiguration.class)
public class SwaggerAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(name = "plus.wcj.heifer.metadata.annotation.IgnoreWebSecurity")
    public SecurityOperationCustomizer ignoreWebSecurityOperationCustomizer() {
        return new SecurityOperationCustomizer();
    }


    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(name = "plus.wcj.heifer.metadata.annotation.ResponseBodyResult")
    public ResponseBodyResultOperationCustomizer resultResponseOperationCustomizer() {
        return new ResponseBodyResultOperationCustomizer();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(name = {
            "org.springframework.security.access.prepost.PostAuthorize",
            "org.springframework.security.access.prepost.PostFilter",
            "org.springframework.security.access.prepost.PreAuthorize",
            "org.springframework.security.access.prepost.PreFilter",
    })
    public SecurityAnnotationOperationCustomizer securityAnnotationOperationCustomizer() {
        return new SecurityAnnotationOperationCustomizer();
    }




    @Bean
    @ConditionalOnMissingBean
    org.springdoc.webmvc.core.service.RequestService requestBuilder(GenericParameterService parameterBuilder, RequestBodyService requestBodyService,
                                                                    OperationService operationService, Optional<List<ParameterCustomizer>> parameterCustomizers,
                                                                    SpringDocParameterNameDiscoverer localSpringDocParameterNameDiscoverer,
                                                                    Optional<List<FieldToIgnoreOperationCustomizer>> fieldToIgnoreOperationCustomizers) {
        return new RequestService(parameterBuilder, requestBodyService, operationService, parameterCustomizers,
                localSpringDocParameterNameDiscoverer, fieldToIgnoreOperationCustomizers);
    }
}
