package plus.wcj.heifer.common.swagger;

import org.springdoc.core.customizers.ParameterCustomizer;
import org.springdoc.core.discoverer.SpringDocParameterNameDiscoverer;
import org.springdoc.core.service.GenericParameterService;
import org.springdoc.core.service.OperationService;
import org.springdoc.core.service.RequestBodyService;
import org.springframework.core.MethodParameter;
import plus.wcj.heifer.common.swagger.customizers.FieldToIgnoreOperationCustomizer;

import java.util.List;
import java.util.Optional;

/**
 * @author changjin wei(魏昌进)
 * @since 2023/6/10
 */
public class RequestService extends org.springdoc.webmvc.core.service.RequestService {

    private final Optional<List<FieldToIgnoreOperationCustomizer>> fieldToIgnoreOperationCustomizers;

    public RequestService(GenericParameterService parameterBuilder, RequestBodyService requestBodyService,
                          OperationService operationService, Optional<List<ParameterCustomizer>> parameterCustomizers,
                          SpringDocParameterNameDiscoverer localSpringDocParameterNameDiscoverer,
                          Optional<List<FieldToIgnoreOperationCustomizer>> fieldToIgnoreOperationCustomizers) {
        super(parameterBuilder, requestBodyService, operationService, parameterCustomizers, localSpringDocParameterNameDiscoverer);
        this.fieldToIgnoreOperationCustomizers = fieldToIgnoreOperationCustomizers;
    }


    public boolean isParamToIgnore(MethodParameter parameter) {
        return fieldToIgnoreOperationCustomizers.isPresent()
                && fieldToIgnoreOperationCustomizers.get().stream().anyMatch(fieldToIgnoreOperationCustomizer -> fieldToIgnoreOperationCustomizer.customize(parameter))
                || super.isParamToIgnore(parameter);
    }
}
