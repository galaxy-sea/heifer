package plus.wcj.heifer.boot.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import plus.wcj.heifer.boot.entity.rbac.user.RbacUser;

import java.util.Date;
import java.util.Optional;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/8/19
 */
@SpringBootTest
public class WcjTest {
    @Autowired
    private Mongodb mongodb;
    @Autowired
    private Redis redis;

    @Test
    public void test() {
        RbacUser rbacUser = new RbacUser();
        rbacUser.setId(111L);
        rbacUser.setRbacOrgId(1L);
        rbacUser.setUsername("weichangjin");
        rbacUser.setPhone("15395777477");
        rbacUser.setEmail("469753862@qq.com");
        rbacUser.setPassword("4697523862");
        rbacUser.setNickname("awd");
        rbacUser.setAccountNonExpired(false);
        rbacUser.setAccountNonLocked(false);
        rbacUser.setCredentialsNonExpired(false);
        rbacUser.setEnabled(false);
        rbacUser.setCreateTime(new Date());
        rbacUser.setCreateBy(1L);
        rbacUser.setUpdateTime(new Date());
        rbacUser.setUpdateBy(1L);
        // wcj.insert(rbacUser);

        redis.save(rbacUser);
        redis.save(rbacUser.setId(2222L));


        Optional<RbacUser> byId = redis.findById(2222L);
        System.out.println(byId);
    }

    @Test
    public void get() {

        // RbacUser rbacUser1 = redis.findById()


    }


}