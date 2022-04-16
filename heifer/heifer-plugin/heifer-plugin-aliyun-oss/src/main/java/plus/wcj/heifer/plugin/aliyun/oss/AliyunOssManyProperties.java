package plus.wcj.heifer.plugin.aliyun.oss;


import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author changjinwei
 * @since 2021/7/19
 */
@Data
@ConfigurationProperties(prefix = "heifer.aliyun")
public class AliyunOssManyProperties {

    private Map<String, AliyunOssProperties> oss = new LinkedHashMap<>();
}
