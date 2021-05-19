package plus.wcj.heifer.boot.common.security.userdetails;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色 DAO
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-07 16:20
 */
public interface RoleDao extends BaseMapper<Role> {
    /**
     * 根据用户id 查询角色列表
     *
     * @param userId 用户id
     *
     * @return 角色列表
     */
    List<Role> selectByUserId(@Param("userId") Long userId);
}
