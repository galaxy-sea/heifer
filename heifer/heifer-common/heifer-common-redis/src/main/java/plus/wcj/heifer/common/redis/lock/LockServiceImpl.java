package plus.wcj.heifer.common.redis.lock;

import lombok.RequiredArgsConstructor;

import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/7/26
 */
@SuppressWarnings("unused")
@Component
@RequiredArgsConstructor
public class LockServiceImpl implements LockService {

    private final RedisLockRegistry redisLockRegistry;

    @Override
    public void lock(String lockKey) {
        Lock lock = this.redisLockRegistry.obtain(lockKey);
        //noinspection AlibabaLockShouldWithTryFinally
        lock.lock();
    }

    @Override
    public boolean tryLock(String lockKey) {
        Lock lock = this.redisLockRegistry.obtain(lockKey);
        return lock.tryLock();
    }

    @Override
    public boolean tryLock(String lockKey, long time, TimeUnit unit) {
        Lock lock = this.redisLockRegistry.obtain(lockKey);
        try {
            return lock.tryLock(time, unit);
        } catch (InterruptedException e) {
            return false;
        }
    }

    @Override
    public void unlock(String lockKey) {
        Lock lock = this.redisLockRegistry.obtain(lockKey);
        lock.unlock();
    }
}
