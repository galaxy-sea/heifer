package plus.wcj.heifer.common.security;


import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 自定义UserDetails查询
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-10 10:29
 */
public interface HeiferUserDetailsService {

    default List<String> getAllPermission(Long rbacTenantId, Long rbacAccountId){
        return new ArrayList<>();
    }

}
