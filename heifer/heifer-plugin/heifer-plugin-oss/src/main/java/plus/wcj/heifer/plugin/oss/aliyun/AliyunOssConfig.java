package plus.wcj.heifer.plugin.oss.aliyun;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 阿里云对象存储的相关bean配置
 *
 * @author changjin wei(魏昌进)
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(AliyunOssProperties.class)
public class AliyunOssConfig {

    @Bean
    public OSS oss(AliyunOssProperties aliyunOssProperties) {
        return new OSSClientBuilder().build(aliyunOssProperties.getEndpoint(), aliyunOssProperties.getAccessId(), aliyunOssProperties.getAccessKey());
    }

    @Bean
    public AliyunOssServer aliyunOssServer(OSS oss, AliyunOssProperties aliyunOssProperties) {
        return new AliyunOssServer(oss, aliyunOssProperties);
    }

    @Bean
    public OssController ossController(AliyunOssServer aliyunOssServer) {
        return new OssController(aliyunOssServer);
    }
}
