package plus.wcj.heifer.boot.manager.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/7/26
 */
public interface LockService {

    /**
     * @param lockKey lockKey
     */
    void lock(String lockKey);

    /**
     * @param lockKey lockKey
     *
     * @return T获得锁，F未获得锁
     */
    boolean tryLock(String lockKey);

    /**
     * @param lockKey lockKey
     * @param time time
     * @param unit unit
     *
     * @return T获得锁，F未获得锁
     */
    boolean tryLock(String lockKey, long time, TimeUnit unit);

    /**
     * @param lockKey lockKey
     */
    void unlock(String lockKey);
}
