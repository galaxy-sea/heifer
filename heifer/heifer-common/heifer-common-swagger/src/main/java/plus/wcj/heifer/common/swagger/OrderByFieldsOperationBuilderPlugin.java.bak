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
import com.fasterxml.classmate.ResolvedType;
import plus.wcj.heifer.common.mybatisplus.validation.OrderByValid;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import org.springframework.core.annotation.Order;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static plus.wcj.heifer.common.swagger.HtmlTool.b;
import static plus.wcj.heifer.common.swagger.HtmlTool.p;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/8/12
 */
@Order(SwaggerPluginSupport.OAS_PLUGIN_ORDER + 101)
public class OrderByFieldsOperationBuilderPlugin implements OperationBuilderPlugin {
    @Override
    public void apply(OperationContext context) {
        List<ResolvedMethodParameter> parameters = context.getParameters();


        for (ResolvedMethodParameter resolvedMethodParameter : parameters) {
            Class<?> erasedType = resolvedMethodParameter.getParameterType().getErasedType();
            if (IPage.class.isAssignableFrom(erasedType)) {
                Optional<OrderByValid> annotation = resolvedMethodParameter.findAnnotation(OrderByValid.class);
                List<String> orderItems = this.toOrderItems(annotation);

                if (CollectionUtils.isEmpty(orderItems)) {
                    orderItems = this.toOrderItems(resolvedMethodParameter);
                }

                this.excludeField(orderItems, annotation);

                if (!CollectionUtils.isEmpty(orderItems)) {
                    this.joinNotes(orderItems, context);
                }
                return;
            }
        }
    }

    @SuppressWarnings({"OptionalUsedAsFieldOrParameterType", "OptionalIsPresent"})
    private void excludeField(List<String> orderItems, Optional<OrderByValid> annotation) {
        if (annotation.isPresent()) {
            List.of(annotation.get().excludeField()).forEach(orderItems::remove);
        }
    }

    private void joinNotes(List<String> orderItems, OperationContext context) {
        StringBuilder code = new StringBuilder();
        for (String orderItem : orderItems) {
            code.append(HtmlTool.code(orderItem));
        }
        String notes = context.operationBuilder().build().getNotes();
        String order = b("OrderBy: ") + code;

        notes = StringUtils.hasText(notes) ? p(notes, order) : order;
        context.operationBuilder().notes(notes);
    }

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private List<String> toOrderItems(Optional<OrderByValid> annotation) {
        if (annotation.isEmpty() || annotation.get().field() == null || annotation.get().field().length == 0) {
            return List.of();
        }
        return Arrays.stream(annotation.get().field()).toList();
    }

    private List<String> toOrderItems(ResolvedMethodParameter resolvedMethodParameter) {
        ResolvedType boundType = resolvedMethodParameter.getParameterType().getTypeBindings().getBoundType(0);
        if (boundType == null) {
            return List.of();
        }
        Class<?> orderClass = boundType.getErasedType();
        return TableInfoHelper.getAllFields(orderClass)
                              .stream()
                              .map(Field::getName)
                              .toList();
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }


}
