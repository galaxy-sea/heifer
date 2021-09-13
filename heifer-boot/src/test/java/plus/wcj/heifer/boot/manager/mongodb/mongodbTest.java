package plus.wcj.heifer.boot.manager.mongodb;
import java.util.Date;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.MongoId;
import plus.wcj.heifer.boot.entity.rbac.user.RbacUser;
import plus.wcj.heifer.boot.repository.mongo.RbacUserMongo;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/7/28
 */
@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class mongodbTest {

    private final RbacUserMongo rbacUserMongo;

    @Test
    public void insert() {
        RbacUser rbacUser = new RbacUser();
        rbacUser.setId(123L);
        rbacUser.setRbacOrgId(0L);
        rbacUser.setUsername("weichangjin");

        rbacUserMongo.save(rbacUser);
    }




}
