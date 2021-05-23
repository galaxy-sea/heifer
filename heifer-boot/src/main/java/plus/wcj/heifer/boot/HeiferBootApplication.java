package plus.wcj.heifer.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
@MapperScans({
        @MapperScan("plus.wcj.heifer.boot.dao.*"),
        @MapperScan("plus.wcj.heifer.boot.common.security.userdetails.mapper")
})
public class HeiferBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeiferBootApplication.class, args);
    }

}
