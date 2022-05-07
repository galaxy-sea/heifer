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
