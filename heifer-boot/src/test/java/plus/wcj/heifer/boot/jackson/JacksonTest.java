package plus.wcj.heifer.boot.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import plus.wcj.heifer.boot.common.mvc.result.Result;

@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JacksonTest {
    private final ObjectMapper objectMapper;

    @Test
    public void test() throws JsonProcessingException {
        String jsonString = objectMapper.writeValueAsString(Result.success());


        Result result = objectMapper.readValue(jsonString, Result.class);
        System.out.println(result);


    }
}
