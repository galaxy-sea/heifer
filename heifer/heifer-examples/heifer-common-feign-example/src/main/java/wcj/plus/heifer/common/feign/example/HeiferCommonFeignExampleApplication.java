package wcj.plus.heifer.common.feign.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class HeiferCommonFeignExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeiferCommonFeignExampleApplication.class, args);
    }

}
