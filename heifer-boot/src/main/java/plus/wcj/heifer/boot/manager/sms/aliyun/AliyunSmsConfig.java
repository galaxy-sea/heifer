package plus.wcj.heifer.boot.manager.sms.aliyun;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.teaopenapi.models.Config;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 阿里云短信服务相关bean配置
 *
 * @author changjin wei(魏昌进)
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(AliyunSmsProperties.class)
public class AliyunSmsConfig {


    @Bean
    public Client createClient(AliyunSmsProperties aliyunSmsProperties) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(aliyunSmsProperties.getAccessId())
                // 您的AccessKey Secret
                .setAccessKeySecret(aliyunSmsProperties.getAccessKey())
                // 访问的域名
                .setEndpoint("dysmsapi.aliyuncs.com");

        return new Client(config);
    }
}