package plus.wcj.heifer.boot.manager.sms.aliyun;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import plus.wcj.heifer.boot.manager.oss.OssProperties;
import plus.wcj.heifer.boot.manager.sms.SmsProperties;

/**
 * @author changjinwei
 * @since 2021/7/19
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "heifer.aliyun.sms")
public class AliyunSmsProperties implements SmsProperties {
    /** 阿里云账号 accessId */
    private String accessId;
    /** 阿里云账号 accessKey */
    private String accessKey;
    /** 短信签名 */
    private String signName;

}
