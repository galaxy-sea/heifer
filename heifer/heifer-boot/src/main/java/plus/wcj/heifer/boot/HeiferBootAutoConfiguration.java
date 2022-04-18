package plus.wcj.heifer.boot;

import org.hibernate.validator.HibernateValidator;
import plus.wcj.heifer.boot.mvc.ResultExceptionHandler;
import plus.wcj.heifer.boot.mvc.ResultResponseBodyAdvice;

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
        converters.sort((o1, o2) -> o1 instanceof MappingJackson2HttpMessageConverter ? -1 : 1);
    }
}
