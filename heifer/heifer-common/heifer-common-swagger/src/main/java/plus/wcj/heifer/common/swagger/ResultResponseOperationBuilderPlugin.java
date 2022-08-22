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

import plus.wcj.heifer.metadata.annotation.ResultResponseBody;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.OperationBuilderPlugin;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/8/13
 */
@Order(SwaggerPluginSupport.OAS_PLUGIN_ORDER + 102)
public class ResultResponseOperationBuilderPlugin implements OperationBuilderPlugin {

    @Override
    public void apply(OperationContext context) {
        Optional<ResultResponseBody> resultResponseBody = context.findAnnotation(ResultResponseBody.class)
                                                                 .or(() -> context.findControllerAnnotation(ResultResponseBody.class));

        if (resultResponseBody.isPresent()) {
            String notes = context.operationBuilder().build().getNotes();
            notes = StringUtils.hasText(notes) ? notes + "<p />" + "**ResponseBody**: `Result`" : "`**ResponseBody**: `Result`";
            context.operationBuilder().notes(notes);
        }

    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }
}
