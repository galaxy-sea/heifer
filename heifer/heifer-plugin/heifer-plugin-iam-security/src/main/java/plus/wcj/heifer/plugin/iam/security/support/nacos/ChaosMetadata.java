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

package plus.wcj.heifer.plugin.iam.security.support.nacos;

import com.alibaba.cloud.nacos.registry.NacosRegistration;
import plus.wcj.heifer.common.nacos.registry.NacosInstancePreRegisteredEventListener;
import plus.wcj.heifer.common.nacos.registry.NacosServiceRegistryManage;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Map;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/8/31
 */
public class ChaosMetadata implements NacosInstancePreRegisteredEventListener {

    private final NacosServiceRegistryManage nacosServiceRegistryManage;

    public ChaosMetadata(NacosServiceRegistryManage nacosServiceRegistryManage) {
        this.nacosServiceRegistryManage = nacosServiceRegistryManage;
    }

    @Override
    public void onApplicationEvent(NacosRegistration nacosRegistration) {

    }

    @Scheduled(cron = "5/30 * * * * ?")
    public void chaosSecretKey() {
        Map<String, String> metadata = nacosServiceRegistryManage.getMetadata();
        metadata.put("chaos", "chaos");
        // TODO: 2022/9/9 changjin wei(魏昌进) 准备使用 虚拟MFA
    }
}
