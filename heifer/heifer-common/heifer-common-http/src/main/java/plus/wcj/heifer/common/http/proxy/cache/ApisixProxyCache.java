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

package plus.wcj.heifer.common.http.proxy.cache;

import plus.wcj.heifer.common.apisix.ApisixCustomizer;
import plus.wcj.heifer.common.apisix.Route;

import java.util.HashMap;
import java.util.Map;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/5/20
 */
public class ApisixProxyCache implements ApisixCustomizer {
    @Override
    public void customizer(Route route) {
        Map<String, Object> plugins = route.getPlugins();
        plugins.put("proxy-cache", new HashMap<String, Object>() {{

        }});
    }
}
