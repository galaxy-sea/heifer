package plus.wcj.heifer.boot.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import plus.wcj.heifer.boot.extension.tenant.Tenant;

import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/7/8
 */
@SpringBootTest
public class SqlTest {

    @Autowired
    private TestDao testDao;


    @Test
    public void testSql1() {

    }

    @Test
    public void testSql2() {
        Tenant name = new Tenant(1L, "name", 1L, 1L, "1,2,3", false);
        List<Long> longs = testDao.test1(name);

        System.out.println(longs);

    }

}
