package plus.wcj.heifer.boot.manager.oss.aliyun;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;


@EnableConfigurationProperties(AliyunOssProperties.class)
@AllArgsConstructor
public class AlibabaOssConfig {

    private final AliyunOssProperties aliyunOssProperties;

    @Bean
    public OSS oss() {
        return new OSSClientBuilder().build(aliyunOssProperties.getEndpoint(), aliyunOssProperties.getAccessId(), aliyunOssProperties.getAccessKey());
    }

}
