package plus.wcj.heifer.common.redis.data;

import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;

import java.time.Duration;
import java.util.Random;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/1/6
 */
@SuppressWarnings({"NullableProblems", "ConstantConditions"})
public class RandomTtlRedisCache extends RedisCache {
    private final int timeToLiveOffset;
    private final long timeToLive;

    @SuppressWarnings("ReplacePseudorandomGenerator")
    private final Random random = new Random();


    /**
     * Create new {@link org.springframework.data.redis.cache.RedisCache}.
     *
     * @param name must not be {@literal null}.
     * @param cacheWriter must not be {@literal null}.
     * @param cacheConfig must not be {@literal null}.
     */
    protected RandomTtlRedisCache(String name, RedisCacheWriter cacheWriter, RedisCacheConfiguration cacheConfig, int timeToLiveOffset) {
        super(name, cacheWriter, cacheConfig);
        this.timeToLiveOffset = timeToLiveOffset;
        this.timeToLive = cacheConfig.getTtl().toMillis();
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
        if (timeToLiveOffset <= 0) {
            return super.getCacheConfiguration().getTtl();
        }
        return Duration.ofMillis(timeToLive + random.nextInt(timeToLiveOffset + 1));
    }
}
