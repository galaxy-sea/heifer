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

package plus.wcj.heifer.common.discovery;

import org.springframework.cloud.client.discovery.event.InstancePreRegisteredEvent;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.event.EventListener;

/**
 * 再向注册中心注册前做一些特殊操作
 *
 * @author changjin wei(魏昌进)
 * @since 2022/8/30
 */
public interface InstancePreRegisteredEventListener<T extends Registration> {

    /**
     * 注册中心注册前的事件
     *
     * @param instancePreRegisteredEvent An event to fire before a service is registered.
     */
    @EventListener
    default void onApplicationEvent(InstancePreRegisteredEvent instancePreRegisteredEvent) {
        @SuppressWarnings("unchecked")
        T registration = (T) instancePreRegisteredEvent.getRegistration();
        onApplicationEvent(registration);
    }

    /**
     * 注册中心注册前的事件
     *
     * @param registration nacos注册实例
     */
    void onApplicationEvent(T registration);

}
