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

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/8/12
 */
public class OrderByFieldsOperationBuilderPlugin implements OperationBuilderPlugin {

    // TODO: 2022/8/12 changjin wei(魏昌进) 未测试， 未注册bean托管


    @Override
    public void apply(OperationContext context) {
        List<ResolvedMethodParameter> parameters = context.getParameters();


        for (ResolvedMethodParameter resolvedMethodParameter : parameters) {
            Class<?> erasedType = resolvedMethodParameter.getParameterType().getErasedType();
            if (IPage.class.isAssignableFrom(erasedType)) {
                Optional<OrderByValid> annotation = resolvedMethodParameter.findAnnotation(OrderByValid.class);
                List<String> orderItems = null;
                if (annotation.isEmpty() || annotation.get().field() == null || annotation.get().field().length == 0) {
                    ResolvedType boundType = resolvedMethodParameter.getParameterType().getTypeBindings().getBoundType(0);
                    if (boundType != null) {
                        Class<?> orderClass = boundType.getErasedType();
                        orderItems = TableInfoHelper.getAllFields(orderClass)
                                                    .stream()
                                                    .map(Field::getName)
                                                    .toList();

                    }
                } else {
                    String[] field = annotation.get().field();
                    orderItems = Arrays.asList(field);
                }

                if (!CollectionUtils.isEmpty(orderItems)) {
                    StringJoiner stringJoiner = new StringJoiner(", ");
                    for (String orderItem : orderItems) {
                        stringJoiner.add(orderItem);
                    }
                    String notes = context.operationBuilder().build().getNotes();
                    notes = StringUtils.hasText(notes) ? notes + "<br />" + stringJoiner : stringJoiner.toString();
                    context.operationBuilder().notes(notes);
                }
                return;
            }
        }
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return SwaggerPluginSupport.pluginDoesApply(delimiter);
    }


}
