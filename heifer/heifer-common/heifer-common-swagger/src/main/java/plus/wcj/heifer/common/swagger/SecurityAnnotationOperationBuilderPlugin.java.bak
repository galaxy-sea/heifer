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

import plus.wcj.heifer.metadata.annotation.IgnoreWebSecurity;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import org.springframework.core.annotation.Order;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.util.StringUtils;

import java.util.Optional;

import static plus.wcj.heifer.common.swagger.HtmlTool.ALIZARIN;
import static plus.wcj.heifer.common.swagger.HtmlTool.b;
import static plus.wcj.heifer.common.swagger.HtmlTool.code;
import static plus.wcj.heifer.common.swagger.HtmlTool.p;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/6/20
 */
@Order(SwaggerPluginSupport.OAS_PLUGIN_ORDER + 100)
public class SecurityAnnotationOperationBuilderPlugin implements OperationBuilderPlugin {

    @Override
    public void apply(final OperationContext context) {
        StringBuffer securityNotes = new StringBuffer();

        Optional<IgnoreWebSecurity> ignoreWebSecurity = context.findAnnotation(IgnoreWebSecurity.class)
                                                               .or(() -> context.findControllerAnnotation(IgnoreWebSecurity.class));
        if (ignoreWebSecurity.isEmpty()) {
            securityNotes.append(b("Authorization: ")).append(code("required ", ALIZARIN));
        } else {
            securityNotes.append(b("Authorization: ")).append(code("IgnoreWebSecurity", ALIZARIN));
        }

        context.findAnnotation(PostAuthorize.class)
               .or(() -> context.findControllerAnnotation(PostAuthorize.class))
               .ifPresent(ann -> securityNotes.append(b("PostAuthorize: ")).append(code(ann.value())));

        context.findAnnotation(PostFilter.class)
               .or(() -> context.findControllerAnnotation(PostFilter.class))
               .ifPresent(ann -> securityNotes.append(b("PostFilter: ")).append(code(ann.value())));

        context.findAnnotation(PreAuthorize.class)
               .or(() -> context.findControllerAnnotation(PreAuthorize.class))
               .ifPresent(ann -> securityNotes.append(b("PreAuthorize: ")).append(code(ann.value())));

        context.findAnnotation(PreFilter.class)
               .or(() -> context.findControllerAnnotation(PreFilter.class))
               .ifPresent(ann -> securityNotes.append(b("PreFilter: ")).append(code(ann.value())));

        if (securityNotes.length() > 0) {
            String notes = context.operationBuilder().build().getNotes();
            notes = StringUtils.hasText(notes) ? p(notes, securityNotes.toString()) : securityNotes.toString();
            context.operationBuilder().notes(notes);
        }
    }

    @Override
    public boolean supports(final DocumentationType delimiter) {
        return true;
    }
}
