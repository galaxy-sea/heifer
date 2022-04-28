package plus.wcj.heifer.plugin.iam.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.plugin.iam.entity.IamRoleAuthority;

import java.util.List;

/**
 * <p>
 * 角色拥有功能权限关系表 Mapper 接口
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-04-23
 */
public interface IamRoleAuthorityDao extends BaseMapper<IamRoleAuthority> {

    default List<IamRoleAuthority> selectByIamRoleId(@Param("iamRoleId") Long iamRoleId) {
        return this.selectList(new LambdaQueryWrapper<IamRoleAuthority>().eq(IamRoleAuthority::getIamRoleId, iamRoleId));
    }

    default List<IamRoleAuthority> selectByIamTenantId(@Param("iamTenantId") Long iamTenantId) {
        return this.selectList(new LambdaQueryWrapper<IamRoleAuthority>().eq(IamRoleAuthority::getIamTenantId, iamTenantId));
    }

    default List<IamRoleAuthority> selectByIamPermissionId(@Param("iamPermissionId") Long iamPermissionId) {
        return this.selectList(new LambdaQueryWrapper<IamRoleAuthority>().eq(IamRoleAuthority::getIamPermissionId, iamPermissionId));
    }

}
