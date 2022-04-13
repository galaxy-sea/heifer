package plus.wcj.heifer.plugin.rbac;

import org.mybatis.spring.annotation.MapperScan;
import plus.wcj.heifer.matedata.properties.JwtProperties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/1/3
 */
@ComponentScan("plus.wcj.heifer.plugin.rbac")
@MapperScan("plus.wcj.heifer.plugin.rbac.dao")
@EnableConfigurationProperties(JwtProperties.class)
public class RbacAutoConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
