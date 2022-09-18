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

package plus.wcj.heifer.plugin.iam.security.support.registry;

import plus.wcj.heifer.common.discovery.InstancePreRegisteredEventListener;
import plus.wcj.heifer.plugin.iam.security.Constant;

import org.springframework.cloud.client.serviceregistry.Registration;

import java.util.Map;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/8/31
 */
public final class ChaosInstancePreRegisteredEventListener implements InstancePreRegisteredEventListener<Registration> {

    private final ChaosMetadata chaosMetadata;

    public ChaosInstancePreRegisteredEventListener(ChaosMetadata chaosMetadata) {
        this.chaosMetadata = chaosMetadata;
    }


    @Override
    public void onApplicationEvent(Registration registration) {
        this.chaosSecretKey(registration.getMetadata());
    }

    private void chaosSecretKey(Map<String, String> metadata) {
        metadata.put(Constant.CHAOS_KEY, chaosMetadata.getChaos());
    }

}
