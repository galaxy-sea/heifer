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

package plus.wcj.heifer.common.apisix.upstreams;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import plus.wcj.heifer.common.apisix.ApisixCustomizer;
import plus.wcj.heifer.common.apisix.Route;

import org.springframework.boot.web.servlet.context.ServletWebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

/**
 * nacos Upstream 设置
 *
 * <a href="https://apisix.apache.org/zh/docs/apisix/discovery/nacos">基于 Nacos 的服务发现</a>
 *
 * @author changjin wei(魏昌进)
 * @since 2022/5/9
 */
@Order(Ordered.HIGHEST_PRECEDENCE + 100)
public class NacosUpstream implements ApisixCustomizer {

    private NacosDiscoveryProperties nacosDiscoveryProperties;

    public NacosUpstream() {

    }


    @EventListener
    public void init(ServletWebServerInitializedEvent servletWebServerInitializedEvent) {
        nacosDiscoveryProperties = servletWebServerInitializedEvent
                .getApplicationContext().getBean(NacosDiscoveryProperties.class);

    }


    @Override
    public void customizer(Route route) {

        String namespace = StringUtils.hasText(nacosDiscoveryProperties.getNamespace()) ? nacosDiscoveryProperties.getNamespace() : "public";
        String group = nacosDiscoveryProperties.getGroup();
        String service = nacosDiscoveryProperties.getService();
        String id = namespace + "@" + group + "@" + service;
        id = DigestUtils.md5DigestAsHex(id.getBytes(StandardCharsets.UTF_8));

        route.setId(id);

        route.setLabels(new LinkedHashMap<String, String>() {{
            put("namespace", namespace);
            put("group", group);
            put("service", service);
            put("source", "SPRING_CLOUD_ALIBABA");
        }});

        route.setUpstream(new LinkedHashMap<String, Object>() {{
            put("service_name", service);
            put("type", "roundrobin");
            put("discovery_type", "nacos");
            put("discovery_args", new LinkedHashMap<String, String>() {{
                put("namespace_id", namespace);
                put("group_name", group);
            }});
        }});

    }
}
