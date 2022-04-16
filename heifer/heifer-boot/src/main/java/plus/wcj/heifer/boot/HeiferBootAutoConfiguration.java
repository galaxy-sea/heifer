package plus.wcj.heifer.boot;

import org.hibernate.validator.HibernateValidator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/12/20
 */
@Configuration
public class HeiferBootAutoConfiguration {

    @Bean
    public Validator getValidatorFactory() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                                                      .configure()
                                                      // 快速失败模式
                                                      .failFast(true)
                                                      .buildValidatorFactory();
        return validatorFactory.getValidator();
    }

}
