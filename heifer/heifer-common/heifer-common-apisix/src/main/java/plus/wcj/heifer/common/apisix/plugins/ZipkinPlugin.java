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

package plus.wcj.heifer.common.apisix.plugins;

import plus.wcj.heifer.common.apisix.ApisixCustomizer;
import plus.wcj.heifer.common.apisix.Route;

import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <a href="https://apisix.apache.org/zh/docs/apisix/plugins/zipkin">see api</a>
 *
 * @author changjin wei(魏昌进)
 * @since 2022/5/10
 */
@Order(Ordered.HIGHEST_PRECEDENCE + 100)
public class ZipkinPlugin implements ApisixCustomizer {

    private String applicationName;
    private String baseUrl;
    private String apiPath;
    private Float probability;

    public ZipkinPlugin() {
    }

    @EventListener
    public void init(ServletWebServerInitializedEvent servletWebServerInitializedEvent) {
        ConfigurableEnvironment environment = servletWebServerInitializedEvent.getApplicationContext().getEnvironment();
        baseUrl = environment.getProperty("spring.zipkin.base-url");
        apiPath = environment.getProperty("spring.zipkin.api-path");
        probability = environment.getProperty("spring.sleuth.sampler.probability", Float.class);
        applicationName = servletWebServerInitializedEvent.getApplicationContext().getId();
    }

    @Override
    public void customizer(Route route) {
        String endpoint = baseUrl + Objects.toString(apiPath, "api/v2/spans");
        Float sampleRatio = probability == null ? 1 : probability;

        Map<String, Object> plugins = route.getPlugins();
        plugins.put("zipkin", new LinkedHashMap<String, Object>() {{
            put("endpoint", endpoint);
            put("sample_ratio", sampleRatio);
            put("service_name", applicationName+"-APISIX");
        }});
    }


}
