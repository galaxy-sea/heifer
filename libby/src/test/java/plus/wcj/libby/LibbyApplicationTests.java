package plus.wcj.libby;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.GenericTypeResolver;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class LibbyApplicationTests {

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    public void contextLoads() throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(new ArrayList() {{
            add(new User<User>("1", "123", new User("1", "1", null)));
            add(new User("1", "123", new User<>()));
        }});
        JavaType javaType = objectMapper.constructType(GenericTypeResolver.resolveType(List.class, User.class));

        JavaType javaType1 = TypeFactory.defaultInstance().constructParametricType(List.class, User.class);


        Object o = objectMapper.readValue(json, javaType1);
        System.out.println(o);

    }

}
