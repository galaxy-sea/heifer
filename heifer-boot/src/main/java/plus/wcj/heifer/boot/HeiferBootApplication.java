package plus.wcj.heifer.boot;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.mybatis.spring.annotation.MapperScan;
import plus.wcj.heifer.boot.entity.rbac.role.RbacRole;
import plus.wcj.heifer.boot.repository.dao.rbac.role.RbacRoleDao;
import springfox.documentation.oas.annotations.EnableOpenApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import java.util.List;
import java.util.Map;

/**
 * @author changjin wei(魏昌进)
 */
@SpringBootApplication
@EnableOpenApi
@EnableCaching
@EnableEncryptableProperties
@EnableGlobalMethodSecurity(prePostEnabled = true)
@MapperScan({"plus.wcj.heifer.boot.repository.dao.**", "plus.wcj.heifer.boot.common.security.userdetails.dao"})
public class HeiferBootApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(HeiferBootApplication.class, args);
        Map<String, HandlerMethodArgumentResolver> beansOfType = run.getBeansOfType(HandlerMethodArgumentResolver.class);
        beansOfType.forEach((s, handlerMethodArgumentResolver) ->
                                    System.out.println(s + handlerMethodArgumentResolver.getClass())

        );
    }

}
