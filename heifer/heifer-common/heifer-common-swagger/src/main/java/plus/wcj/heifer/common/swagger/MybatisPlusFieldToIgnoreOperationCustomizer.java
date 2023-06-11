package plus.wcj.heifer.common.swagger;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.core.MethodParameter;
import plus.wcj.heifer.common.swagger.customizers.FieldToIgnoreOperationCustomizer;

import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @since 2023/6/10
 */
public class MybatisPlusFieldToIgnoreOperationCustomizer implements FieldToIgnoreOperationCustomizer {

    private static final List<String> IGNORE_FIELD = List.of("countId", "maxLimit", "optimizeCountSql", "pages", "records", "searchCount", "total");
    @Override
    public boolean customize(MethodParameter parameter) {
        if (!IPage.class.isAssignableFrom(parameter.getContainingClass())) {
            return false;
        }
        return IGNORE_FIELD.contains(parameter.getParameterName());
    }
}
