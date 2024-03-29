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

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.info.BuildProperties;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * 注册中心注册前的一些初始化
 *
 * @author changjin wei(魏昌进)
 * @since 2022/9/12
 */
public class RegistryConfiguration {

    @Bean
    public InstancePreRegisteredEventListener<Registration> jdkMetadata() {
        return registration -> {
            Map<String, String> metadata = registration.getMetadata();
            Properties properties = System.getProperties();

            // Java版本
            metadata.put("java.version", properties.getProperty("java.version"));
            // Java厂商
            metadata.put("java.vendor", properties.getProperty("java.vendor"));

            // jvm名称
            metadata.put("java.vm.name", properties.getProperty("java.vm.name"));
            // jvm厂商
            metadata.put("java.vm.vendor", properties.getProperty("java.vm.vendor"));


            // os名称
            metadata.put("os.name", properties.getProperty("os.name"));
            // os版本
            metadata.put("os.version", properties.getProperty("os.version"));
            // os架构
            metadata.put("os.arch", properties.getProperty("os.arch"));
        };
    }

    /**
     * see <a href="https://juejin.cn/post/6962294037122383886#heading-2">maven spring-boot-maven-plugin</a>
     */
    @Bean
    @ConditionalOnBean(BuildProperties.class)
    public InstancePreRegisteredEventListener<Registration> buildPropertiesMetadata(BuildProperties buildProperties) {
        return registration -> {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            Map<String, String> metadata = registration.getMetadata();
            metadata.put("project.version", buildProperties.getVersion());
            metadata.put("project.buildTime", dateTimeFormatter.format(buildProperties.getTime()));
        };
    }

    @Bean
    public InstancePreRegisteredEventListener<Registration> startMetadata() {
        return registration -> {
            Map<String, String> metadata = registration.getMetadata();
            // 项目启动pid
            metadata.put("PID", System.getProperties().getProperty("PID"));
            // 项的启动时间
            metadata.put("startTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        };
    }


}
