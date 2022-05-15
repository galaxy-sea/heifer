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

import plus.wcj.heifer.common.apisix.admin.api.RouteClient;
import plus.wcj.heifer.common.apisix.plugins.CorsPlugin;
import plus.wcj.heifer.common.apisix.plugins.ProxyRewritePlugin;
import plus.wcj.heifer.common.apisix.plugins.ZipkinPlugin;
import plus.wcj.heifer.common.apisix.routes.RoutesCustomizer;
import plus.wcj.heifer.common.apisix.upstreams.NacosUpstream;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/5/4
 */
@EnableFeignClients(basePackages = "plus.wcj.heifer.common.apisix.admin.api")
@EnableConfigurationProperties(ApisixProperties.class)
@ConditionalOnWebApplication
public class ApisixAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(name = "corsPlugin")
    public CorsPlugin corsPlugin() {
        return new CorsPlugin();
    }

    @Bean
    @ConditionalOnMissingBean(name = "proxyRewritePlugin")
    public ProxyRewritePlugin proxyRewritePlugin() {
        return new ProxyRewritePlugin();
    }

    @Bean
    @ConditionalOnMissingBean(name = "routesCustomizer")
    public RoutesCustomizer routesCustomizer() {
        return new RoutesCustomizer();
    }

    @Bean
    @ConditionalOnMissingBean(name = "zipkinPlugin")
    @ConditionalOnBean(name = "zipkinSender")
    public ZipkinPlugin zipkinPlugin() {
        return new ZipkinPlugin();
    }


    @Bean
    @ConditionalOnMissingBean(name = "nacosUpstreamCustomizer")
    @ConditionalOnBean(name = "nacosProperties")
    public NacosUpstream nacosUpstreamCustomizer() {
        return new NacosUpstream();
    }

    @Bean
    public SimpleApisixRegister simpleApisixRegister(RouteClient routeClient,
                                                     ObjectProvider<List<ApisixCustomizer>> apisixCustomizers,
                                                     ApisixProperties apisixProperties) {
        return new SimpleApisixRegister(routeClient, apisixCustomizers, apisixProperties);
    }
}
