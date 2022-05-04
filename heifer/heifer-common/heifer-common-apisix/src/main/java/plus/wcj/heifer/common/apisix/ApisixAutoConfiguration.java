package plus.wcj.heifer.common.apisix;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.registry.NacosServiceRegistryAutoConfiguration;
import plus.wcj.heifer.common.apisix.admin.api.RouteClient;
import plus.wcj.heifer.common.apisix.admin.api.UpstreamClient;
import plus.wcj.heifer.common.apisix.discovery.ApisixRegister;
import plus.wcj.heifer.common.apisix.discovery.NacosApisixRegister;
import plus.wcj.heifer.common.apisix.properties.ApisixProperties;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
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
    public ApisixRegister apisixRegister(NacosDiscoveryProperties nacosDiscoveryProperties, ApisixProperties apisixProperties,
                                         RouteClient routeClient, UpstreamClient upstreamClient) {
        return new NacosApisixRegister(nacosDiscoveryProperties, apisixProperties, routeClient, upstreamClient);
    }

}
