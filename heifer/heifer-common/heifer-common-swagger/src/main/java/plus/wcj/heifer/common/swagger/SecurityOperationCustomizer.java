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

import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import org.springdoc.core.customizers.GlobalOperationCustomizer;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.HandlerMethod;
import plus.wcj.heifer.metadata.annotation.IgnoreWebSecurity;

import java.util.Optional;

import static plus.wcj.heifer.common.swagger.HtmlTool.*;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/6/20
 */
@Order
public class SecurityOperationCustomizer implements GlobalOperationCustomizer {
    @Override
    public Operation customize(Operation operation, HandlerMethod handlerMethod) {
        StringBuffer notes = new StringBuffer();

        Optional.ofNullable(handlerMethod.getMethodAnnotation(IgnoreWebSecurity.class))
                .or(() -> Optional.ofNullable(AnnotatedElementUtils.getMergedAnnotation(handlerMethod.getBeanType(), IgnoreWebSecurity.class)))
                .ifPresentOrElse(ignoreWebSecurity ->
                                notes.append(b("IgnoreWebSecurity: ")).append(code("True ", ALIZARIN)),
                        () -> {
                            notes.append(b("IgnoreWebSecurity: ")).append(code("False", ALIZARIN));
                            addHeader(operation);
                        }
                );

        String description = operation.getDescription();
        operation.setDescription(p(description, notes.toString()));

        return operation;
    }

    private void addHeader(Operation operation) {
        HeaderParameter headerParameter = new HeaderParameter();

        Schema<Object> schema = new Schema<Object>();
        schema.setType("String");

        headerParameter.setName(HttpHeaders.AUTHORIZATION);
        headerParameter.setRequired(Boolean.TRUE);
        headerParameter.setSchema(schema);

        operation.addParametersItem(headerParameter);
    }

}

