package plus.wcj.heifer.plugin.rbac.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.plugin.rbac.pojo.entity.RbacPermission;

import java.util.List;

/**
 * <p>
 * 功能权限 Mapper 接口
 * </p>
 *
 * @author changjinwei
 * @since 2021-11-28
 */
public interface RbacPermissionDao extends BaseMapper<RbacPermission> {

    List<RbacPermission> selectByParentId(@Param("parentId") Long parentId);
}
