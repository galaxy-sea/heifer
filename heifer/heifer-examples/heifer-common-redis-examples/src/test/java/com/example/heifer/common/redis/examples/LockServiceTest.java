package com.example.heifer.common.redis.examples;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.jupiter.api.Test;
import plus.wcj.heifer.common.redis.lock.LockService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/20
 */
@SpringBootTest
@Slf4j
public class LockServiceTest {
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
                log.info(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                log.info(String.valueOf(finalI));
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
                        log.info(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                        log.info(String.valueOf(finalI));
                        Thread.sleep(3000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {

                        this.lockService.unlock(key);
                    }
                } else {
                    log.info("小可爱");
                }

            }
            ).start();
        }

        for (int i = 0; i < 100; i++) {
            Thread.sleep(1000);
        }

    }
}
