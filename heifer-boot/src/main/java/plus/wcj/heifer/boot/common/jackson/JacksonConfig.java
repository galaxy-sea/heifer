package plus.wcj.heifer.boot.common.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author changjin wei(魏昌进)
 * @date 2021/4/28
 */

@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();


        return objectMapper;
    }

}
