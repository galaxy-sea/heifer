package plus.wcj.libby;


import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import java.util.Map;
// import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 *
 */
@SpringBootApplication
// @EnableDiscoveryClient
// @EnableFeignClients
public class LibbyApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LibbyApplication.class, args);
        Map<String, User> beansOfType = context.getBeansOfType(User.class);
        System.out.println(beansOfType);


    }

}
