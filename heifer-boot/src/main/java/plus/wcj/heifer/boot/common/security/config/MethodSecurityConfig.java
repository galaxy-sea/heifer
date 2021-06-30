package plus.wcj.heifer.boot.common.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import plus.wcj.heifer.boot.common.security.config.bean.MethodSecurityExpressionHandler;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/6/29
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new MethodSecurityExpressionHandler();
    }

}
