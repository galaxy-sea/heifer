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

package plus.wcj.heifer.common.feign;

import feign.QueryMapEncoder;
import feign.codec.Decoder;
import feign.optionals.OptionalDecoder;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/8
 */
@Configuration(proxyBeanMethods = false)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
public class FeignConfiguration {

    private final ObjectFactory<HttpMessageConverters> messageConverters;

    public FeignConfiguration(ObjectFactory<HttpMessageConverters> messageConverters) {
        this.messageConverters = messageConverters;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(name = {"plus.wcj.heifer.metadata.bean.Result"})
    public Decoder feignDecoder(ObjectProvider<HttpMessageConverterCustomizer> customizers) {
        return new OptionalDecoder(new ResponseEntityDecoder(new ResultDecoder(new SpringDecoder(this.messageConverters, customizers))));
    }


    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(name = {"com.baomidou.mybatisplus.core.metadata.IPage"})
    public QueryMapEncoder pageSpringQueryMapEncoder() {
        return new PageSpringQueryMapEncoder();
    }

}
