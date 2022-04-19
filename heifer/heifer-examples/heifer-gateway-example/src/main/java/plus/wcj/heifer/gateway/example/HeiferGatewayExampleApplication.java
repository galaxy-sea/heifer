package plus.wcj.heifer.gateway.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HeiferGatewayExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeiferGatewayExampleApplication.class, args);
    }

}
