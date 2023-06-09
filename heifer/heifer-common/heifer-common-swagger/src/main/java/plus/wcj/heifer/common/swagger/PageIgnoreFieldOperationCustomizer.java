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

package plus.wcj.heifer.common.swagger;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.v3.oas.models.Operation;
import org.springdoc.core.customizers.GlobalOperationCustomizer;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.web.method.HandlerMethod;

import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/8/21
 */
@Order
public class PageIgnoreFieldOperationCustomizer implements GlobalOperationCustomizer {

    private static final List<String> IGNORE_FIELD = List.of("countId", "maxLimit", "optimizeCountSql", "pages", "records", "searchCount", "total");

    @Override
    public Operation customize(Operation operation, HandlerMethod handlerMethod) {
        for (MethodParameter methodParameter : handlerMethod.getMethodParameters()) {
            if (IPage.class.isAssignableFrom(methodParameter.getParameterType())) {
                operation.getParameters().removeIf(parameter -> IGNORE_FIELD.contains(parameter.getName()));
                break;
            }
        }
        return operation;
    }

}
