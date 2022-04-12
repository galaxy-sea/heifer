package plus.wcj.libby.controller;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import feign.optionals.OptionalDecoder;
import plus.wcj.libby.User;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.GenericTypeResolver;
import org.springframework.util.TypeUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/8
 */
@Configuration
public class FeignConfig {

    @Autowired
    private ObjectFactory<HttpMessageConverters> messageConverters;

    @Autowired
    private ObjectMapper objectMapper;


    @Bean
    public Decoder feignDecoder() {
        return new OptionalDecoder(new ResponseEntityDecoder(new MySpringDecoder(new SpringDecoder(this.messageConverters))));
    }





}

class MySpringDecoder  implements Decoder {

    private Decoder decoder;
    private ObjectMapper objectMapper;

    public MySpringDecoder(Decoder decoder, ObjectMapper objectMapper) {
        this.decoder = decoder;
        this.objectMapper = objectMapper;
    }

    public MySpringDecoder(Decoder decoder) {
        this.decoder = decoder;
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        // JavaType javaType = TypeFactory.defaultInstance().constructParametricType(User.class, type.getClass());
        // GenericTypeResolver.
        ParameterizedTypeImpl make = ParameterizedTypeImpl.make(List.class,new Type[]{type},null);
        Object decode = this.decoder.decode(response, make);
        List<User> users = (List<User>) decode;

        return users.get(0);

    }
}
