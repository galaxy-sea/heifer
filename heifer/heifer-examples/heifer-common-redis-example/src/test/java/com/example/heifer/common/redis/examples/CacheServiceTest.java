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

package com.example.heifer.common.redis.examples;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/20
 */
@SpringBootTest
public class CacheServiceTest {
    @Autowired
    private HelloService helloService;

    @Test
    void timestamp() throws InterruptedException {
        long timestamp1 = helloService.timestamp("1");
        Thread.sleep(10);
        long timestamp2 = helloService.timestamp("1");
        Assert.isTrue(timestamp1 == timestamp2);

        Thread.sleep(10);
        long timestamp3 = helloService.timestamp("2");
        Assert.isTrue(timestamp3 != timestamp2);
    }
}