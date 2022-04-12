package plus.wcj.heifer.plugin.oss.aliyun;


import plus.wcj.heifer.plugin.oss.OssProperties;

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
public class AliyunOssManyProperties implements OssProperties {

    private Map<String, AliyunOssProperties> oss = new LinkedHashMap<>();
}
