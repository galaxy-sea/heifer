package plus.wcj.heifer.plugin.iam.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.plugin.iam.entity.IamAccountManage;

import java.util.List;

/**
 * <p>
 * 账户与租户的绑定关系 Mapper 接口
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-04-23
 */
public interface IamAccountManageDao extends BaseMapper<IamAccountManage> {

    default List<IamAccountManage> selectByIamAccountId(@Param("iamAccountId") Long iamAccountId) {
        return this.selectList(new LambdaQueryWrapper<IamAccountManage>().eq(IamAccountManage::getIamAccountId, iamAccountId));
    }

    default List<IamAccountManage> selectByIamTenantId(@Param("iamTenantId") Long iamTenantId) {
        return this.selectList(new LambdaQueryWrapper<IamAccountManage>().eq(IamAccountManage::getIamTenantId, iamTenantId));
    }

    default List<IamAccountManage> selectByIamDeptId(@Param("iamDeptId") Long iamDeptId) {
        return this.selectList(new LambdaQueryWrapper<IamAccountManage>().eq(IamAccountManage::getIamDeptId, iamDeptId));
    }

}
