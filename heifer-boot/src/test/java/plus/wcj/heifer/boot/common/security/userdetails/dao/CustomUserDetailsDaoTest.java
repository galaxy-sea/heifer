package plus.wcj.heifer.boot.common.security.userdetails.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import plus.wcj.heifer.boot.common.mvc.resolver.tenant.Tenant;

import java.util.ArrayList;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/7/6
 */
@SpringBootTest
class CustomUserDetailsDaoTest {

    @Autowired
    private CustomUserDetailsDao customUserDetailsDao;

    @Test
    void findUserManageTest() {
        Tenant tenant = new Tenant();
        tenant.setUserId(1L);
        tenant.setUsername("test");
        tenant.setOrgId(1L);
        tenant.setDeptId(1L);
        tenant.setDataPowers(new ArrayList() {{
            add(1L);
            add(10L);
        }});
        tenant.setAllPower(false);


        try {
            customUserDetailsDao.findUserManageTest(tenant);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}