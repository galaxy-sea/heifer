package plus.wcj.heifer.plugin.iam.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.plugin.iam.entity.IamPermission;

import java.util.List;

/**
 * <p>
 * 功能权限 Mapper 接口
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-04-23
 */
public interface IamPermissionDao extends BaseMapper<IamPermission> {

    default List<IamPermission> selectByParentId(@Param("parentId") Long parentId) {
        return this.selectList(new LambdaQueryWrapper<IamPermission>().eq(IamPermission::getParentId, parentId));
    }

}
