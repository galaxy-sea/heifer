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

package plus.wcj.heifer.plugin.iam.security.support.openfeign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import plus.wcj.heifer.plugin.iam.security.Constant;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/5/27
 */
@Order(value = Ordered.HIGHEST_PRECEDENCE + 100)
public class JwtRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate template) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes servletRequestAttributes) {
            HttpServletRequest request = servletRequestAttributes.getRequest();
            template.header(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
            template.header(Constant.TENANT_ID, request.getHeader(Constant.TENANT_ID));
        }
    }
}
