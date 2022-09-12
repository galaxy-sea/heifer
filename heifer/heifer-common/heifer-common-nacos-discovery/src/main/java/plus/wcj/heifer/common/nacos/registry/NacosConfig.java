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

package plus.wcj.heifer.common.nacos.registry;

import com.alibaba.cloud.nacos.registry.NacosRegistration;
import com.alibaba.cloud.nacos.registry.NacosServiceRegistry;

import org.springframework.context.annotation.Bean;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/12/31
 */

public class NacosConfig {

    @Bean
    public NacosServiceRegistryManage nacosServiceRegistryManage(NacosServiceRegistry nacosServiceRegistry, NacosRegistration nacosRegistration) {
        return new NacosServiceRegistryManage(nacosServiceRegistry, nacosRegistration);
    }
}
