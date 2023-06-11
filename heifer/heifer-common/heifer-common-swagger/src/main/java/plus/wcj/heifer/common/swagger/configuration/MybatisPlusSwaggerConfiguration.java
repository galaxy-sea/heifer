package plus.wcj.heifer.common.swagger.configuration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import plus.wcj.heifer.common.swagger.MybatisPlusFieldToIgnoreOperationCustomizer;
import plus.wcj.heifer.common.swagger.OrderByFieldsOperationCustomizer;

/**
 * @author changjin wei(魏昌进)
 * @since 2023/6/10
 */
@Configuration
@ConditionalOnClass(name = "com.baomidou.mybatisplus.core.metadata.IPage")
public class MybatisPlusSwaggerConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public MybatisPlusFieldToIgnoreOperationCustomizer mybatisPlusFieldToIgnoreOperationCustomizer() {
        return new MybatisPlusFieldToIgnoreOperationCustomizer();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(name = "plus.wcj.heifer.common.mybatisplus.validation.OrderByValid")
    public OrderByFieldsOperationCustomizer orderByFieldsOperationCustomizer() {
        return new OrderByFieldsOperationCustomizer();
    }


}
