package plus.wcj.heifer.boot.common.tenant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

/**
 * <p>
 * 客户端
 * </p>
 *
 * @author changjinwei
 * @since 2021-06-06
 */
@Data
@ConfigurationProperties(prefix = "heifer.client")
public class ClientProperties implements Serializable {

    private static final long serialVersionUID = 1L;
    /** 客户端id */
    private Integer id;
    /** 客户端名称 */
    private String name;

}
