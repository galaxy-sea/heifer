package plus.wcj.heifer.boot.repository.dao.rbac.org;

import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.boot.entity.rbac.tenant.RbacDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* <p>
 * 部门 Mapper 接口
 * </p>
*
* @author changjin wei(魏昌进)
* @since 2021-11-22
*/
 public interface RbacDeptDao extends BaseMapper<RbacDept> {

   List<RbacDept> selectByParentId(@Param("parentId") Long parentId);
   List<RbacDept> selectByRbacTenantId(@Param("rbacTenantId") Long rbacTenantId);
 }
