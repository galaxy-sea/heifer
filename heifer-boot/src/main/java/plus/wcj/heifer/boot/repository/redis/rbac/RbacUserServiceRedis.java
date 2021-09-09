package plus.wcj.heifer.boot.repository.redis.rbac;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import plus.wcj.heifer.boot.entity.rbac.user.RbacUser;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/8/24
 */
@Service
public class RbacUserServiceRedis  {

    @Cacheable("user")
    public RbacUser Cacheable(Long id) {
        System.out.println(1111);
        return new RbacUser().setId(id).setUsername("redis cache");
    }

    @CacheEvict("user")
    public RbacUser CacheEvict(Long id) {
        System.out.println(1111);
        return new RbacUser().setId(id).setUsername("redis cache");
    }

    @CachePut("user")
    public RbacUser CachePut(Long id) {
        System.out.println(1111);
        return new RbacUser().setId(id).setUsername("redis cache");
    }



}
