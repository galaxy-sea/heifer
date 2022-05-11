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

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/5/9
 */
@Order(Ordered.HIGHEST_PRECEDENCE + 100)
public class ProxyRewritePlugin implements ApisixCustomizer {

    private String applicationName;

    public ProxyRewritePlugin() {

    }

    @EventListener
    public void init(ServletWebServerInitializedEvent servletWebServerInitializedEvent) {
        applicationName = servletWebServerInitializedEvent.getApplicationContext().getId();
    }


    @Override
    public void customizer(Route route) {
        Map<String, Object> plugins = route.getPlugins();

        plugins.put("proxy-rewrite", new LinkedHashMap<String, Object>() {{
            put("regex_uri", new LinkedList<String>() {{
                add("^/" + applicationName + "/(.*)");
                add(("/$1"));
            }});
        }});
    }
}




