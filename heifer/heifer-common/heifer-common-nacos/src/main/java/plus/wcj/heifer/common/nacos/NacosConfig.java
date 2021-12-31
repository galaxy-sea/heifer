package plus.wcj.heifer.common.nacos;

import com.alibaba.cloud.nacos.registry.NacosRegistrationCustomizer;

import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/12/31
 */

public class NacosConfig {

    @Bean
    public NacosRegistrationCustomizer nacosRegistrationCustomizer() {
        return registration -> {
            Map<String, String> metadata = registration.getMetadata();
            Properties properties = System.getProperties();
            // 项目启动pid
            metadata.put("PID", properties.getProperty("PID"));
            // 项的启动时间
            metadata.put("startTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));


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
}
