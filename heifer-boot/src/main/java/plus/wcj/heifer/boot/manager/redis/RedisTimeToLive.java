package plus.wcj.heifer.boot.manager.redis;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.data.redis.core.TimeToLive;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/8/24
 */
public interface RedisTimeToLive {

    long startInclusive = 10 * 60;
    long endExclusive = 100 * 60;

    /**
     * redis 过期时间
     *
     * @return 随机时间
     */
    @TimeToLive
    default long timeToLive() {
        return RandomUtils.nextLong(startInclusive, endExclusive);
    }
}
