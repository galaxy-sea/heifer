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

import feign.Request;
import plus.wcj.heifer.common.feign.loadbalancer.LoadBalancerFeignRequestTransformer;
import plus.wcj.heifer.plugin.iam.security.Constant;
import plus.wcj.heifer.plugin.iam.security.util.ChaosUtils;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/09/18
 */
@Order(value = Ordered.HIGHEST_PRECEDENCE + 100)
public class ChaosRequestTransformer implements LoadBalancerFeignRequestTransformer {

    @Override
    public Request transformRequest(Request request, ServiceInstance instance) {
        String chaos = instance.getMetadata().get(Constant.CHAOS_KEY);
        if (!StringUtils.hasText(chaos)) {
            Map<String, Collection<String>> headers = new HashMap<>(request.headers());
            headers.put(HttpHeaders.AUTHORIZATION, Collections.singleton(ChaosUtils.encoded(chaos)));
            request = Request.create(request.httpMethod(), request.url(), headers, request.body(), request.charset(),
                                     request.requestTemplate());
        }
        return request;
    }
}
