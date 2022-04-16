package plus.wcj.heifer.plugin.aliyun.oss;


import lombok.Getter;
import lombok.Setter;

/**
 * @author changjinwei
 * @since 2021/7/19
 */
@Getter
@Setter
public class AliyunOssProperties {
    /** 阿里云账号 accessId */
    private String accessId;
    /** 阿里云账号 accessKey */
    private String accessKey;
    /** oss accessId */
    private String bucket;
    /** oss endpoint */
    private String endpoint;
    /** 上传的域名  https://aliyuncs.com/ 注意斜杠结尾 */
    private String host;
    /** 授权过期时间 默认 0， 单位毫秒 */
    private long expire = 0;
}
