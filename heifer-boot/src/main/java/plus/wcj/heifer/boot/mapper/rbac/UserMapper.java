package plus.wcj.heifer.boot.mapper.rbac;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import plus.wcj.heifer.boot.entity.User;

public interface UserMapper extends BaseMapper<User> {

    Page<User> selectBy(Page<User> page);
}
