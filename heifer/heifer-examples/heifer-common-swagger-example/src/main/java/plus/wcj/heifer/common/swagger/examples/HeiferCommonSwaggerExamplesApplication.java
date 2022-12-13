package plus.wcj.heifer.common.swagger.examples;

import springfox.documentation.oas.annotations.EnableOpenApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/6/21
 */
@SpringBootApplication
@EnableOpenApi
public class HeiferCommonSwaggerExamplesApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeiferCommonSwaggerExamplesApplication.class, args);
    }

}
