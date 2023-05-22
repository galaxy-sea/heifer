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

package com.example.heifer.common.redis.examples;

import plus.wcj.heifer.common.redis.lock.LockService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/20
 */
@SpringBootTest
public class LockServiceTest {
    private static final Logger logger = LoggerFactory.getLogger(LockServiceTest.class);


    @Autowired
    private LockService lockService;


    @Test
    public void lock() throws InterruptedException {
        String key = "lock1";
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                this.lockService.lock(key);
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.info(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                logger.info(String.valueOf(finalI));
                this.lockService.unlock(key);
            }
            ).start();
        }

        for (int i = 0; i < 100; i++) {
            Thread.sleep(1000);
        }

    }

    @Test
    public void lock2() throws InterruptedException {
        String key = "lock2";
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                if (this.lockService.tryLock(key)) {
                    try {
                        logger.info(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                        logger.info(String.valueOf(finalI));
                        Thread.sleep(3000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {

                        this.lockService.unlock(key);
                    }
                } else {
                    logger.info("小可爱");
                }

            }
            ).start();
        }

        for (int i = 0; i < 100; i++) {
            Thread.sleep(1000);
        }

    }
}
