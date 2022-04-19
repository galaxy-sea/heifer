package plus.wcj.heifer.common.mybatis.plus.example;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/19
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    User selectTest();

    User selectEnum(@Param("user") User user);
}
