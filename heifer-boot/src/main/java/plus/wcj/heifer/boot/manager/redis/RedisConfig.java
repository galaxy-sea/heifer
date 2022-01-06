package plus.wcj.heifer.boot.manager.redis;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/7/23
 */
@Configuration
@EnableCaching
@EnableRedisRepositories(basePackages = {"plus.wcj.heifer.boot.repository.redis"})
public class RedisConfig {

    @Bean
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<?, ?> template = new StringRedisTemplate(redisConnectionFactory);
        template.setEnableTransactionSupport(false);
        return template;
    }


    @Bean
    public CacheManager dynamicTtlCacheManager(RedisConnectionFactory factory) {
        RedisCacheWriter redisCacheWriter = RedisCacheWriter.lockingRedisCacheWriter(factory);
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        return new RandomTtlRedisCacheManager(redisCacheWriter, config, Duration.ofMinutes(10), Duration.ofMinutes(20));
    }

}
