package plus.wcj.heifer.boot.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import plus.wcj.heifer.boot.entity.rbac.user.RbacUser;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/8/24
 */
public interface RbacUserMongo extends MongoRepository<RbacUser, Long> {

    RbacUser findByUsername(@Param("username") String username);
}
