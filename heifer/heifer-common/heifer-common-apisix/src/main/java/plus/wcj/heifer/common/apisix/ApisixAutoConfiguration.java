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

package plus.wcj.heifer.common.apisix;

import com.alibaba.cloud.nacos.registry.NacosServiceRegistryAutoConfiguration;
import plus.wcj.heifer.common.apisix.admin.api.RouteClient;
import plus.wcj.heifer.common.apisix.admin.api.UpstreamClient;
import plus.wcj.heifer.common.apisix.discovery.ApisixRegister;
import plus.wcj.heifer.common.apisix.discovery.NacosApisixRegister;
import plus.wcj.heifer.common.apisix.properties.ApisixProperties;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/5/4
 */
@EnableFeignClients(basePackages = "plus.wcj.heifer.common.apisix.admin.api")
@EnableConfigurationProperties(ApisixProperties.class)
@AutoConfigureAfter(NacosServiceRegistryAutoConfiguration.class)
public class ApisixAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(ApisixRegister.class)
    public ApisixRegister<?> apisixRegister(ApisixProperties apisixProperties,
                                            RouteClient routeClient, UpstreamClient upstreamClient) {
        return new NacosApisixRegister(apisixProperties, routeClient, upstreamClient);
    }

}
