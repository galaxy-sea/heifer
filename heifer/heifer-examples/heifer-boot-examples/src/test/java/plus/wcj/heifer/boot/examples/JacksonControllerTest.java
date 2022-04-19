package plus.wcj.heifer.boot.examples;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;

import java.math.BigDecimal;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/19
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JacksonControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void jackson() {
        String body = restTemplate.getForEntity("/jackson", String.class).getBody();
        Assert.isTrue(body.equals("{\"name\":\"name\",\"status\":\"2\",\"age\":\"3\",\"money\":\"10\",\"gradeEnum\":\"PRIMARY\"}"));
    }

    @Test
    public void jacksonRead() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Accept", "application/json");
        httpHeaders.set("Content-Type", "application/json");

        ValidatedDto validatedDto = new ValidatedDto();
        validatedDto.setName("name");
        validatedDto.setStatus(2);
        validatedDto.setAge(3L);
        validatedDto.setMoney(new BigDecimal("10"));
        validatedDto.setGradeEnum(GradeEnum.PRIMARY);


        HttpEntity requestEntity = new HttpEntity("{\"name\":\"name\",\"status\":\"2\",\"age\":\"3\",\"money\":\"10\",\"gradeEnum\":\"PRIMARY\"}", httpHeaders);

        String body = restTemplate.exchange("/jackson", HttpMethod.POST, requestEntity, String.class).getBody();
        Assert.isTrue(body.equals("{\"name\":\"name\",\"status\":\"2\",\"age\":\"3\",\"money\":\"10\",\"gradeEnum\":\"PRIMARY\"}"));
    }

    @Test
    public void string() {
        String body = restTemplate.getForEntity("/jackson/string", String.class).getBody();
        Assert.isTrue("hello".equals(body));
    }
}