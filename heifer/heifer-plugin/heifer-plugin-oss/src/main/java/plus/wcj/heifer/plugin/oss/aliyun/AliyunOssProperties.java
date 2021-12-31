package plus.wcj.heifer.plugin.oss.aliyun;


import lombok.Getter;
import lombok.Setter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author changjinwei
 * @since 2021/7/19
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "heifer.aliyun.oss")
public class AliyunOssProperties implements OssProperties {
    /** 阿里云账号 accessId */
    private String accessId;
    /** 阿里云账号 accessKey */
    private String accessKey;
    /** oss accessId */
    private String bucket;
    /** oss 阿里云 accessId */
    private String endpoint;
    /** 上传的域名 */
    private String host;
    /** 授权过期时间 默认 0， 单位毫秒 */
    private long expire = 0;
}
