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

package plus.wcj.heifer.common.polaris.discovery.example;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/9/25
 */
@SpringBootTest
public class HeiferCommonPolarisDiscoveryExampleApplicationTest {
    @Autowired
    private DiscoveryClient discoveryClient;


    @Test
    public void contextLoads() {
        List<ServiceInstance> instances = discoveryClient.getInstances("heifer-common-nacos-discovery-example");
        Assert.isTrue(!CollectionUtils.isEmpty(instances));
    }
}