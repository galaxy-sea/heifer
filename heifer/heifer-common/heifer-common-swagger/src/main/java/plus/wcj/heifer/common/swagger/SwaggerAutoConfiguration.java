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

package plus.wcj.heifer.common.swagger;

import plus.wcj.heifer.metadata.iam.User;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author changjin wei(魏昌进)
 * @since 2022/6/20
 */
@Configuration
@PropertySource("classpath:application-swagger.properties")
@AutoConfigureBefore(com.github.xiaoymin.knife4j.spring.configuration.Knife4jAutoConfiguration.class)
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerAutoConfiguration {

    @Bean
    @ConditionalOnClass(name = {
            "org.springframework.security.access.prepost.PostAuthorize",
            "org.springframework.security.access.prepost.PostFilter",
            "org.springframework.security.access.prepost.PreAuthorize",
            "org.springframework.security.access.prepost.PreFilter",
    })
    public SecurityAnnotationOperationBuilderPlugin securityAnnotationOperationBuilderPlugin() {
        return new SecurityAnnotationOperationBuilderPlugin();
    }

    @Bean
    @ConditionalOnClass(name = {
            "plus.wcj.heifer.common.mybatisplus.validation.OrderByValid"
    })
    public OrderByFieldsOperationBuilderPlugin orderByFieldsOperationBuilderPlugin() {
        return new OrderByFieldsOperationBuilderPlugin();
    }

    @Bean
    @ConditionalOnClass(name = {
            "plus.wcj.heifer.metadata.annotation.ResponseBodyResult"
    })
    public ResultResponseOperationBuilderPlugin resultResponseOperationBuilderPlugin() {
        return new ResultResponseOperationBuilderPlugin();
    }


    @Bean
    @ConditionalOnClass(name = {
            "com.baomidou.mybatisplus.core.metadata.IPage"
    })
    public PageIgnoreOperationBuilderPlugin pageIgnoreOperationBuilderPlugin() {
        return new PageIgnoreOperationBuilderPlugin();
    }


    @Bean
    @ConditionalOnMissingBean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(this.apiInfo())
                .ignoredParameterTypes(HttpServletResponse.class, HttpServletRequest.class, User.class)
                // .enableUrlTemplating(true)
                .select()
                // .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("heifer")
                .description("heifer api")
                .contact(new Contact("changjin wei", "wcj.plus", "wcj@wcj.plus"))
                .version("1.0")
                .build();
    }

}
