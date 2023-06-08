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
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import io.swagger.v3.oas.models.Operation;
import org.springdoc.core.customizers.GlobalOperationCustomizer;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import plus.wcj.heifer.common.mybatisplus.validation.OrderByValid;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static plus.wcj.heifer.common.swagger.HtmlTool.b;
import static plus.wcj.heifer.common.swagger.HtmlTool.p;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/8/12
 */
@Component
public class OrderByFieldsOperationCustomizer implements GlobalOperationCustomizer {
    @Override
    public Operation customize(Operation operation, HandlerMethod handlerMethod) {
        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();

        for (MethodParameter methodParameter : methodParameters) {
            Class<?> parameterType = methodParameter.getParameterType();
            if (IPage.class.isAssignableFrom(parameterType)) {
                Optional<OrderByValid> annotation = Optional.ofNullable(methodParameter.getParameterAnnotation(OrderByValid.class));

                List<String> orderItems = this.toOrderItems(annotation);
                if (CollectionUtils.isEmpty(orderItems)) {
                    orderItems = this.toOrderItems(methodParameter);
                }
                this.excludeField(orderItems, annotation);

                if (!CollectionUtils.isEmpty(orderItems)) {
                    this.joinNotes(orderItems, operation);
                }
                return operation;
            }
        }
        return operation;
    }

    @SuppressWarnings({"OptionalUsedAsFieldOrParameterType", "OptionalIsPresent"})
    private void excludeField(List<String> orderItems, Optional<OrderByValid> annotation) {
        if (annotation.isPresent()) {
            List.of(annotation.get().excludeField()).forEach(orderItems::remove);
        }
    }

    private void joinNotes(List<String> orderItems, Operation operation) {
        StringBuilder code = new StringBuilder();
        for (String orderItem : orderItems) {
            code.append(HtmlTool.code(orderItem));
        }
        String notes = operation.getDescription();
        String order = b("OrderBy: ") + code;

        notes = StringUtils.hasText(notes) ? p(notes, order) : order;
        operation.setDescription(notes);
    }


    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private List<String> toOrderItems(Optional<OrderByValid> annotation) {
        if (annotation.isEmpty() || annotation.get().field() == null || annotation.get().field().length == 0) {
            return List.of();
        }
        return Arrays.stream(annotation.get().field()).toList();
    }

    private List<String> toOrderItems(MethodParameter methodParameter) {
        String typeName = ((ParameterizedType) methodParameter.getGenericParameterType()).getActualTypeArguments()[0].getTypeName();

        if (typeName == null) {
            return List.of();
        }
        Class<?> orderClass = null;
        try {
            orderClass = Class.forName(typeName);
            return TableInfoHelper.getAllFields(orderClass)
                    .stream()
                    .map(Field::getName)
                    .toList();
        } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
        }
    }
}
