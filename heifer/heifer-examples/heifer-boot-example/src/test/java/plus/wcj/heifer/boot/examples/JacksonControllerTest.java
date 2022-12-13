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