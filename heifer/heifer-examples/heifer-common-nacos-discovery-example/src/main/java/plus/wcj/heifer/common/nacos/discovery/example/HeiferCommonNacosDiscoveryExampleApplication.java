package plus.wcj.heifer.common.nacos.discovery.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class HeiferCommonNacosDiscoveryExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(HeiferCommonNacosDiscoveryExampleApplication.class, args);
    }

}
