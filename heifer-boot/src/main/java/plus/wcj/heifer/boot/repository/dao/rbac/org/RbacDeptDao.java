package plus.wcj.heifer.boot.repository.dao.rbac.org;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.boot.entity.rbac.tenant.RbacDept;

import java.util.List;

/**
* <p>
 * 部门 Mapper 接口
 * </p>
*
* @author changjinwei
* @since 2021-11-28
*/
 public interface RbacDeptDao extends BaseMapper<RbacDept> {

   List<RbacDept> selectByParentId(@Param("parentId") Long parentId);
   List<RbacDept> selectByRbacTenantId(@Param("rbacTenantId") Long rbacTenantId);
 }
