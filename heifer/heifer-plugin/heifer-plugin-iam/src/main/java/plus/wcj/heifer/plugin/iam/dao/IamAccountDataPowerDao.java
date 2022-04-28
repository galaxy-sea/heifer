package plus.wcj.heifer.plugin.iam.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.plugin.iam.entity.IamAccountDataPower;

import java.util.List;

/**
 * <p>
 * 账户数据权限 Mapper 接口
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-04-23
 */
public interface IamAccountDataPowerDao extends BaseMapper<IamAccountDataPower> {

    default List<IamAccountDataPower> selectByIamAccountId(@Param("iamAccountId") Long iamAccountId) {
        return this.selectList(new LambdaQueryWrapper<IamAccountDataPower>().eq(IamAccountDataPower::getIamAccountId, iamAccountId));
    }

    default List<IamAccountDataPower> selectByIamDeptId(@Param("iamDeptId") Long iamDeptId) {
        return this.selectList(new LambdaQueryWrapper<IamAccountDataPower>().eq(IamAccountDataPower::getIamDeptId, iamDeptId));
    }

    default List<IamAccountDataPower> selectByIamTenantId(@Param("iamTenantId") Long iamTenantId) {
        return this.selectList(new LambdaQueryWrapper<IamAccountDataPower>().eq(IamAccountDataPower::getIamTenantId, iamTenantId));
    }

}
