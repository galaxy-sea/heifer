package plus.wcj.heifer.boot.common.security.userdetails;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 用户 DAO
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-07 16:18
 */
public interface UserDao extends BaseMapper<User> {
    /**
     * 根据用户名、邮箱、手机号查询用户
     *
     * @param username 用户名
     * @param email 邮箱
     * @param phone 手机号
     *
     * @return 用户信息
     */
    Optional<User> findByUsernameOrEmailOrPhone(@Param("username") String username, @Param("email") String email, @Param("phone") String phone);

    /**
     * 根据用户名列表查询用户列表
     *
     * @param usernameList 用户名列表
     *
     * @return 用户列表
     */
    List<User> findByUsernameIn(@Param("usernameList") List<String> usernameList);
}
