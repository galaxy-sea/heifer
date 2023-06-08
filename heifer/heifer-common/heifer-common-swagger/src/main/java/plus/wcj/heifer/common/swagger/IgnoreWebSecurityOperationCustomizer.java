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
import org.springdoc.core.customizers.GlobalOperationCustomizer;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import plus.wcj.heifer.metadata.annotation.IgnoreWebSecurity;

import java.util.Optional;

import static plus.wcj.heifer.common.swagger.HtmlTool.*;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/6/20
 */
@Order
public class IgnoreWebSecurityOperationCustomizer implements GlobalOperationCustomizer {
    @Override
    public Operation customize(Operation operation, HandlerMethod handlerMethod) {
        StringBuffer notes = new StringBuffer();

        Optional.ofNullable(handlerMethod.getMethodAnnotation(IgnoreWebSecurity.class))
                .or(() -> Optional.ofNullable(AnnotatedElementUtils.getMergedAnnotation(handlerMethod.getBeanType(), IgnoreWebSecurity.class)))
                .ifPresentOrElse(ignoreWebSecurity ->
                                notes.append(b("IgnoreWebSecurity: ")).append(code("True ", ALIZARIN)),
                        () -> notes.append(b("IgnoreWebSecurity: ")).append(code("False", ALIZARIN))
                );

        String description = operation.getDescription();
        description = StringUtils.hasText(description) ? p(description, notes.toString()) : notes.toString();
        operation.setDescription(description);

        return operation;
    }
}

