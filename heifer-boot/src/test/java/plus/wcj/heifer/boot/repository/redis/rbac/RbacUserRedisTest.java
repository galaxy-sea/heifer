package plus.wcj.heifer.boot.repository.redis.rbac;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import plus.wcj.heifer.boot.entity.rbac.account.RbacUser;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/8/24
 */
@SpringBootTest
public class RbacUserRedisTest {

    @Autowired
    private RbacUserRedis rbacUserRedis;

    @Autowired
    private RbacUserServiceRedis rbacUserServiceRedis;

    @Test
    public void test1111() {
        RbacUser rbacUser = new RbacUser();
        rbacUser.setId(0L);
        rbacUser.setRbacOrgId(0L);
        rbacUser.setUsername("wodeisjie");
        rbacUser.setPhone("");
        rbacUser.setEmail("");
        rbacUser.setPassword("");
        rbacUser.setNickname("");
        rbacUser.setAccountNonExpired(false);
        rbacUser.setAccountNonLocked(false);
        rbacUser.setCredentialsNonExpired(false);
        rbacUser.setEnabled(false);
        rbacUser.setCreateTime(new Date());
        rbacUser.setCreateBy(0L);
        rbacUser.setUpdateTime(new Date());
        rbacUser.setUpdateBy(0L);


        rbacUserRedis.save(rbacUser);

        RbacUser wodeisjie = rbacUserRedis.findByUsername("wodeisjie");

        System.out.println(wodeisjie);
    }
        @Test
    public void test() {
        RbacUser rbacUser = new RbacUser();
        rbacUser.setId(1L);
        rbacUser.setUsername("wei");
        rbacUserRedis.save(rbacUser);


        System.out.println(rbacUserRedis.findById(1L));
    }

    @Test
    public void test2() {




        // System.out.println(rbacUserRedis.findByUsername("wei"));
        System.out.println(rbacUserRedis.findById(1L));

    }
    @Test
    public void test3() {

        RbacUser rbacUser = rbacUserServiceRedis.Cacheable(123L);
        System.out.println(rbacUser);


        rbacUser = rbacUserServiceRedis.Cacheable(456L);
        System.out.println(rbacUser);

        rbacUser = rbacUserServiceRedis.Cacheable(789L);
        System.out.println(rbacUser);
    }



    @Test
    public void test4() {
        RbacUser rbacUser = rbacUserServiceRedis.CacheEvict(123L);
        System.out.println(rbacUser);


        rbacUser = rbacUserServiceRedis.CacheEvict(456L);
        System.out.println(rbacUser);

    }


    @Test
    public void test5() {
        RbacUser rbacUser = rbacUserServiceRedis.CachePut(123L);
        System.out.println(rbacUser);


        rbacUser = rbacUserServiceRedis.CachePut(123L);
        System.out.println(rbacUser);

    }





}