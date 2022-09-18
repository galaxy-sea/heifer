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

package plus.wcj.heifer.plugin.iam.security.support;

import plus.wcj.heifer.common.discovery.InstancePreRegisteredEventListener;
import plus.wcj.heifer.plugin.iam.security.support.registry.ChaosInstancePreRegisteredEventListener;
import plus.wcj.heifer.plugin.iam.security.support.registry.ChaosMetadata;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.Bean;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/9/19
 */
@ConditionalOnProperty(value = "spring.cloud.service-registry.auto-registration.enabled", matchIfMissing = true)
public class IamRegistryAutoAutoConfiguration {

    @Bean
    public ChaosMetadata chaosMetadata() {
        return new ChaosMetadata();
    }

    @Bean
    public InstancePreRegisteredEventListener<Registration> chaosMetadata(ChaosMetadata chaosMetadata) {
        return new ChaosInstancePreRegisteredEventListener(chaosMetadata);
    }
}
