package plus.wcj.heifer.plugin.aliyun.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import lombok.RequiredArgsConstructor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


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


    @Bean(name = OssConstants.OSS_TASK_EXECUTOR_BEAN_NAME)
    @ConditionalOnMissingBean
    public ExecutorService ossTaskExecutor() {
        int coreSize = Runtime.getRuntime().availableProcessors();
        return new ThreadPoolExecutor(coreSize, 128, 60, TimeUnit.SECONDS,
                                      new SynchronousQueue<>()
        );
    }
}
