package plus.wcj.heifer.boot.common.mvc.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@Primary
public class JacksonConfig {

    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        // 数字不转科学计算法
        objectMapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);
        // 枚举类型
        // objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);

        // long转换成string
        SimpleModule simpleModule = new SimpleModule();
        objectMapper.registerModule(simpleModule);

        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);

        return objectMapper;
    }
}
