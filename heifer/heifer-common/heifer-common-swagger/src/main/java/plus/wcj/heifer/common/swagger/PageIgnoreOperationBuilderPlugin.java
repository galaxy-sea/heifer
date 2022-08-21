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
import com.github.xiaoymin.knife4j.spring.plugin.AbstractOperationBuilderPlugin;
import springfox.documentation.service.ListVendorExtension;
import springfox.documentation.service.ResolvedMethodParameter;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.OperationContext;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/8/21
 */
@Order(SwaggerPluginSupport.OAS_PLUGIN_ORDER + 101)
public class PageIgnoreOperationBuilderPlugin extends AbstractOperationBuilderPlugin {

    public static final String IGNORE_PARAMETER_EXTENSION_NAME = "x-ignoreParameters";

    public static final String[] IGNORE = new String[]{"countId", "maxLimit", "optimizeCountSql", "pages", "records", "searchCount", "total"};


    @Override
    public void apply(OperationContext context) {
        if (this.hasPage(context)) {
            addExtensionParameters(IGNORE, IGNORE_PARAMETER_EXTENSION_NAME, context);
        }
    }


    @Override
    public boolean supports(DocumentationType delimiter) {
        return true;
    }

    private boolean hasPage(OperationContext context) {
        List<ResolvedMethodParameter> parameters = context.getParameters();
        for (ResolvedMethodParameter resolvedMethodParameter : parameters) {
            Class<?> erasedType = resolvedMethodParameter.getParameterType().getErasedType();
            if (IPage.class.isAssignableFrom(erasedType)) {
                return true;
            }
        }
        return false;
    }

    private void addExtensionParameters(String[] params, String extensionName, OperationContext context) {
        if (params != null && params.length > 0) {
            Map<String, Boolean> map = new HashMap<>();
            for (String ignore : params) {
                if (ignore != null && !"".equals(ignore) && !"null".equals(ignore)) {
                    map.put(ignore, true);
                }
            }
            if (map.size() > 0) {
                List<Map<String, Boolean>> maps = new ArrayList<>();
                maps.add(map);
                ListVendorExtension<Map<String, Boolean>> listVendorExtension = new ListVendorExtension<>(extensionName, maps);
                List<VendorExtension> vendorExtensions = new ArrayList<>();
                vendorExtensions.add(listVendorExtension);
                //context.operationBuilder().extensions(Lists.newArrayList(listVendorExtension));
                context.operationBuilder().extensions(vendorExtensions);
            }
        }
    }


}
