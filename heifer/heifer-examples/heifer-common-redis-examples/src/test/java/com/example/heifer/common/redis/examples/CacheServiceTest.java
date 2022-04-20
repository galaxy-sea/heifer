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