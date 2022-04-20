package plus.wcj.heifer.boot.examples;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/18
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResultControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void hello() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/hello", String.class);
        Assert.isTrue("200".equals(String.valueOf(responseEntity.getStatusCodeValue())));
        Assert.isTrue("{\"code\":\"200\",\"message\":\"OK\",\"data\":{\"hello\":\"hello\",\"world\":\"world\"}}".equals(responseEntity.getBody()));
    }

    @Test
    public void result() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/result", String.class);
        Assert.isTrue("200".equals(String.valueOf(responseEntity.getStatusCodeValue())));
        Assert.isTrue("{\"code\":\"200\",\"message\":\"OK\",\"data\":{\"hello\":\"hello\",\"world\":\"world\"}}".equals(responseEntity.getBody()));
    }

    @Test
    public void error401() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/error401", String.class);
        Assert.isTrue("401".equals(String.valueOf(responseEntity.getStatusCodeValue())));
        Assert.isTrue("{\"code\":\"401\",\"message\":\"Unauthorized\"}".equals(responseEntity.getBody()));
    }

    @Test
    public void string() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/string", String.class);
        Assert.isTrue("200".equals(String.valueOf(responseEntity.getStatusCodeValue())));
        Assert.isTrue("{\"code\":\"200\",\"message\":\"OK\",\"data\":\"hello, world\"}".equals(responseEntity.getBody()));
    }

    @Test
    public void validator() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("/validator", String.class);
        Assert.isTrue("400".equals(String.valueOf(responseEntity.getStatusCodeValue())));
        Assert.isTrue("{\"code\":\"400\",\"message\":\"Bad Request\",\"data\":{\"defaultMessage\":\"不能为null\",\"field\":\"name\",\"code\":\"NotNull\"}}".equals(responseEntity.getBody()));
    }
}