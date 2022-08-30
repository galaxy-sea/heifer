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

package plus.wcj.heifer.common.nacos.discovery;

import com.alibaba.cloud.nacos.registry.NacosRegistration;

import org.springframework.cloud.client.discovery.event.InstancePreRegisteredEvent;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 *
 * 再向注册中心注册前做一些特殊操作
 *
 * @author changjin wei(魏昌进)
 * @since 2022/8/30
 */
public interface InstancePreRegisteredEventListener {


    @EventListener
    @Order(Ordered.HIGHEST_PRECEDENCE)
    default void onApplicationEvent(InstancePreRegisteredEvent instancePreRegisteredEvent) {
        Registration registration = instancePreRegisteredEvent.getRegistration();
        onApplicationEvent((NacosRegistration) registration);
    }

    /**
     * 注册的时候会触发一次， 如果nacos相关配置改变也会触发， 如修改metadata
     *
     * @param nacosRegistration nacos注册实例
     */
    void onApplicationEvent(NacosRegistration nacosRegistration);

}
