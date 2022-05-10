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

package plus.wcj.heifer.common.apisix.routes;

import plus.wcj.heifer.common.apisix.ApisixCustomizer;
import plus.wcj.heifer.common.apisix.Route;

import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.cloud.commons.util.InetUtils;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/5/9
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RoutesCustomizer implements ApisixCustomizer {

    private String applicationName;

    private int port;
    private InetUtils inetUtils;

    public RoutesCustomizer() {
    }

    @EventListener
    public void init(ServletWebServerInitializedEvent servletWebServerInitializedEvent) {
        port = servletWebServerInitializedEvent.getWebServer().getPort();
        applicationName = servletWebServerInitializedEvent.getApplicationContext().getId();
        inetUtils = servletWebServerInitializedEvent.getApplicationContext().getBean(InetUtils.class);
    }

    @Override
    public void customizer(Route route) {
        String id = DigestUtils.md5DigestAsHex(applicationName.getBytes(StandardCharsets.UTF_8));
        route.setId(id);
        route.setName(applicationName);
        route.setDesc("Heifer create");
        route.setUri("/" + applicationName + "/*");
        route.setLabels(new LinkedHashMap<String, String>() {{
            put("service", applicationName);
            put("source", "SPRING_BOOT");
        }});

        route.setUpstream(new LinkedHashMap<String, Object>() {{
            put("type", "roundrobin");
            put("nodes", new LinkedHashMap<String, Integer>() {{
                put(inetUtils.findFirstNonLoopbackHostInfo().getIpAddress() + ":" + port, 1);
            }});
        }});
    }
}
