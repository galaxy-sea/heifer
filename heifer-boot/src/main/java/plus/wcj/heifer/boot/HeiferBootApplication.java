package plus.wcj.heifer.boot;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
@EnableEncryptableProperties
@MapperScan({"plus.wcj.heifer.boot.dao.**", "plus.wcj.heifer.boot.common.security.userdetails.dao"})
public class HeiferBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeiferBootApplication.class, args);
    }

}
