package plus.wcj.heifer.boot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import plus.wcj.heifer.boot.entity.rbac.user.RbacUser;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/8/19
 */
public interface Mongodb extends MongoRepository<RbacUser, Long> {

    RbacUser findByPhone(String phone);

    RbacUser findBy(RbacUser user);
}
