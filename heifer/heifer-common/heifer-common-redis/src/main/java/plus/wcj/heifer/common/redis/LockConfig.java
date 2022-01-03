package plus.wcj.heifer.common.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.redis.util.RedisLockRegistry;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/7/26
 */
@Configuration
public class LockConfig {

    @Bean(destroyMethod = "destroy")
    @ConditionalOnBean(RedisConnectionFactory.class)
    public RedisLockRegistry redisLockRegistry(RedisConnectionFactory redisConnectionFactory) {
        return new RedisLockRegistry(redisConnectionFactory, "lock");
    }
}
