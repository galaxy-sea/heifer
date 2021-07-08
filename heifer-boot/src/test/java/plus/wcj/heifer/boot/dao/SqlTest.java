package plus.wcj.heifer.boot.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
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



        List list = testDao.test2(new Long[]{1L,2L,3L});
        System.out.println(list);

    }

}
