package plus.wcj.heifer.boot.extension.dto;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.integration.redis.util.RedisLockRegistry;
import plus.wcj.heifer.boot.entity.rbac.user.RbacUser;

import java.util.concurrent.locks.Lock;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/7/22
 */
@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RedisTest {

    private final RedisTemplate<String, Object> redisTemplate;
    private final RedisLockRegistry redisLockRegistry;


    @Test
    public void setValue() {
        redisTemplate.opsForValue().set("string", "test");
        redisTemplate.opsForValue().set("user", new RbacUser().setId(1L).setNickname("nickname"));


        redisTemplate.opsForList().rightPush("Lists", 1);
        redisTemplate.opsForList().rightPush("Lists", 2);
        redisTemplate.opsForList().rightPush("Lists", 3);

    }

    @Test
    public void getValue() {
        System.out.println(redisTemplate.opsForValue().get("string"));
        System.out.println(redisTemplate.opsForValue().get("user"));
    }

    @Test
    public void lockTest() {
        Lock lock = redisLockRegistry.obtain("test");
        lock.lock();


    }

}
