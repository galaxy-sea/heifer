package plus.wcj.heifer.plugin.rbac.security;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/1/13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented

@EnableWebSecurity
@EnableFeignClients(clients = UserPrincipalClient.class)
public @interface EnableRbacWebSecurity {
}
