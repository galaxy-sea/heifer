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

package plus.wcj.heifer.common.polaris.registry;

import com.tencent.cloud.polaris.registry.PolarisRegistration;

import org.springframework.cloud.client.discovery.event.InstancePreRegisteredEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

 /**
 * 强制修改Polaris.metadata的数据类型.
 * @see <a href="https://github.com/Tencent/spring-cloud-tencent/pull/583">pull 583</a>
 *
 * @author changjin wei(魏昌进)
 * @since 2022/9/12
 */
public class ModifyMetadataTypePreRegisteredEventListener implements PolarisInstancePreRegisteredEventListener {

     @Override
     @Order(Ordered.HIGHEST_PRECEDENCE)
     public void onApplicationEvent(InstancePreRegisteredEvent instancePreRegisteredEvent) {
         PolarisInstancePreRegisteredEventListener.super.onApplicationEvent(instancePreRegisteredEvent);
     }

     @Override
    public void onApplicationEvent(PolarisRegistration polarisRegistration) {
        Field metadataField = ReflectionUtils.findField(polarisRegistration.getClass(), "metadata");
        if (metadataField != null) {
            Map<String, String> metadata = new HashMap<>(polarisRegistration.getMetadata());
            ReflectionUtils.makeAccessible(metadataField);
            ReflectionUtils.setField(metadataField, polarisRegistration, metadata);
        }
    }
}
