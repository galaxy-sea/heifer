package plus.wcj.heifer.plugin.iam.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.plugin.iam.entity.IamAccountRoleRel;

import java.util.List;

/**
 * <p>
 * 账户拥有角色关系表 Mapper 接口
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-04-23
 */
public interface IamAccountRoleRelDao extends BaseMapper<IamAccountRoleRel> {

    default List<IamAccountRoleRel> selectByIamAccountId(@Param("iamAccountId") Long iamAccountId) {
        return this.selectList(new LambdaQueryWrapper<IamAccountRoleRel>().eq(IamAccountRoleRel::getIamAccountId, iamAccountId));
    }

    default List<IamAccountRoleRel> selectByIamRoleId(@Param("iamRoleId") Long iamRoleId) {
        return this.selectList(new LambdaQueryWrapper<IamAccountRoleRel>().eq(IamAccountRoleRel::getIamRoleId, iamRoleId));
    }

    default List<IamAccountRoleRel> selectByIamTenantId(@Param("iamTenantId") Long iamTenantId) {
        return this.selectList(new LambdaQueryWrapper<IamAccountRoleRel>().eq(IamAccountRoleRel::getIamTenantId, iamTenantId));
    }

}
