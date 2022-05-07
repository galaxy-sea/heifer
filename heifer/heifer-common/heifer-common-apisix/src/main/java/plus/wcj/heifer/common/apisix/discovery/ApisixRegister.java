package plus.wcj.heifer.common.apisix.discovery;

import org.springframework.cloud.client.discovery.event.InstanceRegisteredEvent;
import org.springframework.context.event.EventListener;

/**
 * 将本地信息注册到Apisix中
 *
 * @author changjin wei(魏昌进)
 * @since 2022/5/4
 */
public interface ApisixRegister<T> {

    /**
     * 注册
     */

    @EventListener
    void register(InstanceRegisteredEvent<T> instanceRegisteredEvent);

}
