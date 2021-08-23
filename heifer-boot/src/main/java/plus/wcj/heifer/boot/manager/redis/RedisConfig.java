package plus.wcj.heifer.boot.manager.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/7/23
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<?, ?> template = new StringRedisTemplate(redisConnectionFactory);
        template.setEnableTransactionSupport(false);
        return template;
    }
}
