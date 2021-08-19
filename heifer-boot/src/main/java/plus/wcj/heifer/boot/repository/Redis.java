package plus.wcj.heifer.boot.repository;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import plus.wcj.heifer.boot.entity.rbac.user.RbacUser;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/8/19
 */
public interface Redis extends KeyValueRepository<RbacUser, Long> {

}
