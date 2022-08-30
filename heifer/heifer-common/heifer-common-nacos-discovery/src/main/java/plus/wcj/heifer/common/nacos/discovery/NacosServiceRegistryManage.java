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

package plus.wcj.heifer.common.nacos.discovery;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.registry.NacosRegistration;
import com.alibaba.cloud.nacos.registry.NacosServiceRegistry;

import java.util.Map;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/8/29
 */
public class NacosServiceRegistryManage {

    private final NacosServiceRegistry nacosServiceRegistry;

    private final NacosRegistration nacosRegistration;

    public NacosServiceRegistryManage(NacosServiceRegistry nacosServiceRegistry, NacosRegistration nacosRegistration) {
        this.nacosServiceRegistry = nacosServiceRegistry;
        this.nacosRegistration = nacosRegistration;
    }

    public void updateMetadata(Map<String, String> metadata) {
        NacosDiscoveryProperties nacosDiscoveryProperties = nacosRegistration.getNacosDiscoveryProperties();
        nacosDiscoveryProperties.setMetadata(metadata);
        String status = nacosDiscoveryProperties.isInstanceEnabled() ? "UP" : "DOWN";
        nacosServiceRegistry.setStatus(nacosRegistration, status);
    }


    public Map<String, String> getMetadata() {
        return nacosRegistration.getMetadata();
    }


}
