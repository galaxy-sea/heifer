package plus.wcj.heifer.boot.manager.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/7/26
 */
public interface LockService {

    void lock(String lockKey);

    boolean tryLock(String lockKey);

    boolean tryLock(String lockKey, long time, TimeUnit unit);

    void unlock(String lockKey);
}
