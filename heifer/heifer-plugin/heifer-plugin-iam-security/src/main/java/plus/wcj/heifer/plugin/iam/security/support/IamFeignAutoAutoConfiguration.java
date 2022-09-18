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

package plus.wcj.heifer.plugin.iam.security.support;

import feign.Feign;
import feign.RequestInterceptor;
import plus.wcj.heifer.plugin.iam.security.support.openfeign.ChaosRequestTransformer;
import plus.wcj.heifer.plugin.iam.security.support.openfeign.JwtRequestInterceptor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/9/18
 */
@ConditionalOnClass(Feign.class)
public class IamFeignAutoAutoConfiguration {


    @Bean
    public ChaosRequestTransformer chaosRequestTransformer() {
        return new ChaosRequestTransformer();
    }

    @Bean
    @ConditionalOnMissingBean
    public RequestInterceptor jwtRequestInterceptor() {
        return new JwtRequestInterceptor();
    }
}
