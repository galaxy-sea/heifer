package plus.wcj.heifer.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableOpenApi
@SpringBootApplication
public class HeiferBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeiferBootApplication.class, args);
    }

}
