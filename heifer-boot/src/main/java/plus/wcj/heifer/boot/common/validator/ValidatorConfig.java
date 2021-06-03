package plus.wcj.heifer.boot.common.validator;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


/**
 * @author changjin wei(魏昌进)
 * @since 2021/6/2
 */

@Configuration
public class ValidatorConfig {

    @Bean
    public LocalValidatorFactoryBean getValidatorFactory() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.getValidationPropertyMap().put("hibernate.validator.fail_fast", "true");
        return localValidatorFactoryBean;
    }

}
