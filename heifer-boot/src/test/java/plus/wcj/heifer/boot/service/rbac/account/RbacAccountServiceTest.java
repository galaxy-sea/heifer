package plus.wcj.heifer.boot.service.rbac.account;

import com.baomidou.mybatisplus.core.incrementer.DefaultIdentifierGenerator;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/12/7
 */
// @SpringBootTest
class RbacAccountServiceTest {

    private IdWorker idWorker;
    // @Autowired
    // private RbacAccountService rbacAccountService;

    @Test
    public void getIdTest(){
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
        System.out.println(IdWorker.getId());
    }

}