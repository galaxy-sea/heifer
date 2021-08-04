package plus.wcj.heifer.boot.manager.lock;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/7/26
 */
@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
class LockServiceTest {


    private final LockService lockService;


    @Test
    public void lock() throws InterruptedException {
        String key = "data";
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
        String key = "data";
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
                    log.info("傻逼");
                }

            }
            ).start();
        }

        for (int i = 0; i < 100; i++) {
            Thread.sleep(1000);
        }

    }
}