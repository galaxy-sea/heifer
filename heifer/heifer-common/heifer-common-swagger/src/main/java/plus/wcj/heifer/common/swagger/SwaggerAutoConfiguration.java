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

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/6/20
 */
@Configuration
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
    @ConditionalOnMissingBean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(this.apiInfo())
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
