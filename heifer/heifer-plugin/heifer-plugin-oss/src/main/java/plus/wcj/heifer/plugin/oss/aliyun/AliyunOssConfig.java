package plus.wcj.heifer.plugin.oss.aliyun;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 阿里云对象存储的相关bean配置
 *
 * @author changjin wei(魏昌进)
 */
@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(AliyunOssManyProperties.class)
public class AliyunOssConfig {

    @Bean(name = "aliyunOssMap")
    public Map<String, OSS> oss(AliyunOssManyProperties aliyunOssManyProperties) {
        Map<String, OSS> ossMap = new LinkedHashMap<>();
        for (Map.Entry<String, AliyunOssProperties> aliyunOssPropertiesEntry : aliyunOssManyProperties.getOss().entrySet()) {
            AliyunOssProperties aliyunOssProperties = aliyunOssPropertiesEntry.getValue();
            OSS oss = new OSSClientBuilder().build(aliyunOssProperties.getEndpoint(), aliyunOssProperties.getAccessId(), aliyunOssProperties.getAccessKey());
            ossMap.put(aliyunOssPropertiesEntry.getKey(), oss);
        }
        return ossMap;
    }

    @Bean
    public AliyunOssServer aliyunOssServer(Map<String, OSS> aliyunOssMap, AliyunOssManyProperties aliyunOssManyProperties) {
        return new AliyunOssServer(aliyunOssMap, aliyunOssManyProperties.getOss());
    }

    @Bean
    public OssController ossController(AliyunOssServer aliyunOssServer) {
        return new OssController(aliyunOssServer);
    }
}
