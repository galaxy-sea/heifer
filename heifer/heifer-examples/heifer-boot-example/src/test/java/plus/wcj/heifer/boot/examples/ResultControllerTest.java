/*
 * Copyright 2021-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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