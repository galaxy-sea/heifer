package plus.wcj.heifer.common.apisix.properties;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * Apisix的配置类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2022-01-13
 */
@Data
@ConfigurationProperties(prefix = "apisix")
public class ApisixProperties {

    /**
     * Apisix 地址
     */
    private String serverAddr = "http://192.168.0.1:9080/";

    private String serverPath = "apisix/admin/";

    /**
     * Apisix token
     */
    private String token = "edd1c9f034335f136f87ad84b625c8f1";


}
