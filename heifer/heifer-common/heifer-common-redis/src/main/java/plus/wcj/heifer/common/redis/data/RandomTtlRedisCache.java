package plus.wcj.heifer.common.redis.data;

import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;

import java.security.SecureRandom;
import java.time.Duration;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/1/6
 */
public class RandomTtlRedisCache extends RedisCache {

    /**
     * 最小失效时间
     */
    private Duration minTtl;

    /**
     * 最大失效时间
     */
    private Duration maxTtl;

    private final int diffMillis;
    private final int minMillis;

    private SecureRandom random = new SecureRandom();


    /**
     * Create new {@link org.springframework.data.redis.cache.RedisCache}.
     *
     * @param name must not be {@literal null}.
     * @param cacheWriter must not be {@literal null}.
     * @param cacheConfig must not be {@literal null}.
     */
    protected RandomTtlRedisCache(String name, RedisCacheWriter cacheWriter, RedisCacheConfiguration cacheConfig, Duration minTtl, Duration maxTtl) {
        super(name, cacheWriter, cacheConfig);
        this.minTtl = minTtl;
        this.maxTtl = maxTtl;
        this.diffMillis = (int) maxTtl.minus(minTtl).toMillis();
        this.minMillis = (int) maxTtl.toMillis();
    }

    @Override
    public void put(Object key, Object value) {
        Object cacheValue = preProcessCacheValue(value);

        if (!isAllowNullValues() && cacheValue == null) {

            throw new IllegalArgumentException(String.format(
                    "Cache '%s' does not allow 'null' values. Avoid storing null via '@Cacheable(unless=\"#result == null\")' or configure RedisCache to allow 'null' via RedisCacheConfiguration.",
                    super.getName()
            ));
        }
        super.getNativeCache().put(super.getName(), super.serializeCacheKey(createCacheKey(key)), serializeCacheValue(cacheValue), generateRandomDuration());

    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        Object cacheValue = preProcessCacheValue(value);

        if (!isAllowNullValues() && cacheValue == null) {
            return get(key);
        }

        byte[] result = super.getNativeCache().putIfAbsent(super.getName(), super.serializeCacheKey(createCacheKey(key)), serializeCacheValue(cacheValue),
                                                           generateRandomDuration()
        );

        if (result == null) {
            return null;
        }

        return new SimpleValueWrapper(fromStoreValue(deserializeCacheValue(result)));
    }


    /**
     * 根据失效时间的范围随机生成一个Duration
     *
     * @return Duration
     */
    private Duration generateRandomDuration() {
        int randomSecond = minMillis + random.nextInt(diffMillis + 1);
        return Duration.ofMillis(randomSecond);
    }
}
