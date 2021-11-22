package plus.wcj.heifer.boot.repository.dao.rbac;

import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.boot.entity.rbac.RbacPermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* <p>
 * 功能权限 Mapper 接口
 * </p>
*
* @author changjin wei(魏昌进)
* @since 2021-11-22
*/
 public interface RbacPermissionDao extends BaseMapper<RbacPermission> {

   List<RbacPermission> selectByParentId(@Param("parentId") Long parentId);
 }
