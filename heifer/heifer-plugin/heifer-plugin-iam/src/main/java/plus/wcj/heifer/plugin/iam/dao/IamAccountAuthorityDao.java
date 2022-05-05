package plus.wcj.heifer.plugin.iam.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.plugin.iam.entity.IamAccountAuthority;

import java.util.List;

/**
 * <p>
 * 账户拥有功能权限表 Mapper 接口
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-04-23
 */
public interface IamAccountAuthorityDao extends BaseMapper<IamAccountAuthority> {

    default List<IamAccountAuthority> selectByIamAccountId(@Param("iamAccountId") Long iamAccountId) {
        return this.selectList(new LambdaQueryWrapper<IamAccountAuthority>().eq(IamAccountAuthority::getIamAccountId, iamAccountId));
    }

    default List<IamAccountAuthority> selectByIamTenantId(@Param("iamTenantId") Long iamTenantId) {
        return this.selectList(new LambdaQueryWrapper<IamAccountAuthority>().eq(IamAccountAuthority::getIamTenantId, iamTenantId));
    }

    default List<IamAccountAuthority> selectByIamPermissionId(@Param("iamPermissionId") Long iamPermissionId) {
        return this.selectList(new LambdaQueryWrapper<IamAccountAuthority>().eq(IamAccountAuthority::getIamPermissionId, iamPermissionId));
    }

}