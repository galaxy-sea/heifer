package plus.wcj.heifer.common.redis.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/7/26
 */
@SuppressWarnings("unused")
public interface LockService {

    /**
     * 锁
     * @param lockKey lockKey
     */
    void lock(String lockKey);

    /**
     *
     * try锁
     *
     * @param lockKey lockKey
     *
     * @return T获得锁，F未获得锁
     */
    boolean tryLock(String lockKey);

    /**
     * try锁
     *
     * @param lockKey lockKey
     * @param time time
     * @param unit unit
     *
     * @return T获得锁，F未获得锁
     */
    boolean tryLock(String lockKey, long time, TimeUnit unit);

    /**
     * 释放锁
     * @param lockKey lockKey
     */
    void unlock(String lockKey);
}
