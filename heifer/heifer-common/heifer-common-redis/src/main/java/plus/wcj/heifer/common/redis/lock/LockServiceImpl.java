/*
 * Copyright 2021-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
