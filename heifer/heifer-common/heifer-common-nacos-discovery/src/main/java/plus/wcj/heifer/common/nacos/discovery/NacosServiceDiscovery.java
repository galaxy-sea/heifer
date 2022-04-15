package plus.wcj.heifer.common.nacos.discovery;

import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/15
 */
public class NacosServiceDiscovery extends com.alibaba.cloud.nacos.discovery.NacosServiceDiscovery {

    public NacosServiceDiscovery(NacosDiscoveryProperties discoveryProperties, NacosServiceManager nacosServiceManager) {
        super(discoveryProperties, nacosServiceManager);
    }


}
