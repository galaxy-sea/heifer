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

package plus.wcj.heifer.boot;

import org.hibernate.validator.HibernateValidator;
import plus.wcj.heifer.boot.web.ResultExceptionHandler;
import plus.wcj.heifer.boot.web.mvc.ResultResponseBodyAdvice;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/12/20
 */
@Configuration
public class HeiferBootAutoConfiguration implements WebMvcConfigurer {

    @Bean
    public Validator getValidatorFactory() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                                                      .configure()
                                                      // 快速失败模式
                                                      .failFast(true)
                                                      .buildValidatorFactory();
        return validatorFactory.getValidator();
    }


    @Bean
    public ResultExceptionHandler resultExceptionHandler(MessageSource messageSource) {
        return new ResultExceptionHandler(messageSource);
    }

    @Bean
    public ResultResponseBodyAdvice resultResponseBodyAdvice() {
        return new ResultResponseBodyAdvice();
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.sort((o1, o2) -> o1.getClass().equals(o2.getClass()) ? 0 : o1 instanceof MappingJackson2HttpMessageConverter ? -1 : 0);
    }
}
