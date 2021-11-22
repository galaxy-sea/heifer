package plus.wcj.heifer.boot.repository.dao.rbac.account;

import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.boot.entity.rbac.account.RbacAccountDataPower;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* <p>
 * 账户数据权限 Mapper 接口
 * </p>
*
* @author changjin wei(魏昌进)
* @since 2021-11-22
*/
 public interface RbacAccountDataPowerDao extends BaseMapper<RbacAccountDataPower> {

   List<RbacAccountDataPower> selectByRbacAccountId(@Param("rbacAccountId") Long rbacAccountId);
   List<RbacAccountDataPower> selectByRbacDeptId(@Param("rbacDeptId") Long rbacDeptId);
 }
