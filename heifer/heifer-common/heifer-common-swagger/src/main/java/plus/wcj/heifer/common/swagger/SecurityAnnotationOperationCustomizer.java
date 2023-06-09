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
import org.springframework.web.method.HandlerMethod;

import java.util.Optional;

import static plus.wcj.heifer.common.swagger.HtmlTool.*;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/6/20
 */
@Order
public class SecurityAnnotationOperationCustomizer implements GlobalOperationCustomizer {

    @Override
    public Operation customize(Operation operation, HandlerMethod handlerMethod) {

        StringBuffer classNote = classSecurityAnnotation(handlerMethod);
        StringBuffer methodNote = methodSecurityAnnotation(handlerMethod);
        noteAppend("class: ", classNote, operation);
        noteAppend("method: ", methodNote, operation);

        return operation;
    }

    private void noteAppend(String prefix, StringBuffer classNote, Operation operation) {
        if (classNote.isEmpty()) {
            return;
        }
        String note = prefix + classNote;
        String description = operation.getDescription();
        operation.setDescription(p(description, note));
    }

    private StringBuffer methodSecurityAnnotation(HandlerMethod handlerMethod) {
        StringBuffer note = new StringBuffer();

        Optional.ofNullable(AnnotatedElementUtils.getMergedAnnotation(handlerMethod.getBeanType(), PostAuthorize.class))
                .ifPresent(ann -> note.append(b("PostAuthorize: ")).append(code(ann.value())));

        Optional.ofNullable(AnnotatedElementUtils.getMergedAnnotation(handlerMethod.getBeanType(), PostFilter.class))
                .ifPresent(ann -> note.append(b("PostFilter: ")).append(code(ann.value())));

        Optional.ofNullable(AnnotatedElementUtils.getMergedAnnotation(handlerMethod.getBeanType(), PreAuthorize.class))
                .ifPresent(ann -> note.append(b("PreAuthorize: ")).append(code(ann.value())));

        Optional.ofNullable(AnnotatedElementUtils.getMergedAnnotation(handlerMethod.getBeanType(), PreFilter.class))
                .ifPresent(ann -> note.append(b("PreFilter: ")).append(code(ann.value())));
        return note;
    }

    private StringBuffer classSecurityAnnotation(HandlerMethod handlerMethod) {
        StringBuffer note = new StringBuffer();

        Optional.ofNullable(handlerMethod.getMethodAnnotation(PostAuthorize.class))
                .ifPresent(ann -> note.append(b("PostAuthorize: ")).append(code(ann.value())));

        Optional.ofNullable(handlerMethod.getMethodAnnotation(PostFilter.class))
                .ifPresent(ann -> note.append(b("PostFilter: ")).append(code(ann.value())));

        Optional.ofNullable(handlerMethod.getMethodAnnotation(PreAuthorize.class))
                .ifPresent(ann -> note.append(b("PreAuthorize: ")).append(code(ann.value())));

        Optional.ofNullable(handlerMethod.getMethodAnnotation(PreFilter.class))
                .ifPresent(ann -> note.append(b("PreFilter: ")).append(code(ann.value())));

        return note;
    }
}

