package com.example.heifer.common.redis.examples;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/20
 */
@Service
public class HelloService {

    @Cacheable(value = "timestamp",key = "#id")
    public long timestamp(String id) {
        return System.currentTimeMillis();
    }
}
