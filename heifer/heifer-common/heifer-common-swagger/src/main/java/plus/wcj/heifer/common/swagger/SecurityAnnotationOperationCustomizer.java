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
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.method.HandlerMethod;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static plus.wcj.heifer.common.swagger.HtmlTool.ALIZARIN;
import static plus.wcj.heifer.common.swagger.HtmlTool.b;
import static plus.wcj.heifer.common.swagger.HtmlTool.code;
import static plus.wcj.heifer.common.swagger.HtmlTool.p;

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
        noteAppend(b("class( ", ALIZARIN), classNote, b(")", ALIZARIN), operation);
        noteAppend(b("method( ", ALIZARIN), methodNote, b(")", ALIZARIN), operation);
        return operation;
    }

    private void noteAppend(String prefix, StringBuffer classNote, String suffix, Operation operation) {
        if (classNote.isEmpty()) {
            return;
        }
        String note = prefix + classNote + suffix;
        String description = operation.getDescription();
        operation.setDescription(p(description, note));
    }

    private StringBuffer classSecurityAnnotation(HandlerMethod handlerMethod) {
        StringBuffer note = new StringBuffer();

        Optional.ofNullable(AnnotatedElementUtils.getMergedAnnotation(handlerMethod.getBeanType(), PostAuthorize.class))
                .ifPresent(ann -> appendAnnotationValue(ann, note, "PostAuthorize: "));

        Optional.ofNullable(AnnotatedElementUtils.getMergedAnnotation(handlerMethod.getBeanType(), PostFilter.class))
                .ifPresent(ann -> appendAnnotationValue(ann, note, "PostFilter: "));

        Optional.ofNullable(AnnotatedElementUtils.getMergedAnnotation(handlerMethod.getBeanType(), PreAuthorize.class))
                .ifPresent(ann -> appendAnnotationValue(ann, note, "PreAuthorize: "));

        Optional.ofNullable(AnnotatedElementUtils.getMergedAnnotation(handlerMethod.getBeanType(), PreFilter.class))
                .ifPresent(ann -> appendAnnotationValue(ann, note, "PreFilter: "));
        return note;
    }

    private StringBuffer methodSecurityAnnotation(HandlerMethod handlerMethod) {
        StringBuffer note = new StringBuffer();

        Optional.ofNullable(handlerMethod.getMethodAnnotation(PostAuthorize.class))
                .ifPresent(ann -> appendAnnotationValue(ann, note, "PostAuthorize: "));

        Optional.ofNullable(handlerMethod.getMethodAnnotation(PostFilter.class))
                .ifPresent(ann -> appendAnnotationValue(ann, note, "PostFilter: "));

        Optional.ofNullable(handlerMethod.getMethodAnnotation(PreAuthorize.class))
                .ifPresent(ann -> appendAnnotationValue(ann, note, "PreAuthorize: "));

        Optional.ofNullable(handlerMethod.getMethodAnnotation(PreFilter.class))
                .ifPresent(ann -> appendAnnotationValue(ann, note, "PreFilter: "));
        return note;
    }


    private void appendAnnotationValue(Annotation annotation, StringBuffer note, String text) {
        note.append(b(text)).append(code(getAuthority(AnnotationUtils.getAnnotationAttributes(annotation).get("value")))).append(' ');
    }

    private String getAuthority(Object value) {
        String regex = "'(.*?)'";

        String input = (String) value;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            return matcher.group(1);
        }
        return input;
    }

}

