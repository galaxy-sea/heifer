package plus.wcj.heifer.boot;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.mybatis.spring.annotation.MapperScan;
import springfox.documentation.oas.annotations.EnableOpenApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

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
        SpringApplication.run(HeiferBootApplication.class, args);
    }

}
