package plus.wcj.heifer.common.swagger.customizers;

import org.springframework.core.MethodParameter;

/**
 * @author changjin wei(魏昌进)
 * @since 2023/6/10
 */
public interface FieldToIgnoreOperationCustomizer {

    /**
     * Is field to ignore boolean.
     *
     * @param parameter the parameter
     * @return the boolean
     */
    boolean customize(MethodParameter parameter);

}
