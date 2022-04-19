package plus.wcj.heifer.common.nacos.discovery.example;

import com.alibaba.nacos.api.exception.NacosException;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;

@SpringBootTest
public class HeiferCommonNacosDiscoveryExampleApplicationTests {
    @Autowired
    private DiscoveryClient discoveryClient;


    @Test
    public void contextLoads() throws NacosException, InterruptedException {
        List<ServiceInstance> instances = discoveryClient.getInstances("heifer-boot-examples");
        Assert.isTrue(!CollectionUtils.isEmpty(instances));
    }

}
