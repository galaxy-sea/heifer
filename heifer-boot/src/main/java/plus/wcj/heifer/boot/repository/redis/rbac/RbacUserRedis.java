package plus.wcj.heifer.boot.repository.redis.rbac;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.data.repository.query.Param;
import plus.wcj.heifer.boot.entity.rbac.user.RbacUser;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/8/24
 */
public interface RbacUserRedis extends KeyValueRepository<RbacUser, Long> {

    RbacUser findByUsername(@Param("username") String username);
}
