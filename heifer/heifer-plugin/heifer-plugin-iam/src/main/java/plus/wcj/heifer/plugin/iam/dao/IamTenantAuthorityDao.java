package plus.wcj.heifer.plugin.iam.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.plugin.iam.entity.IamTenantAuthority;

import java.util.List;

/**
 * <p>
 * 租户拥有的权限 Mapper 接口
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-04-23
 */
public interface IamTenantAuthorityDao extends BaseMapper<IamTenantAuthority> {

    default List<IamTenantAuthority> selectByIamTenantId(@Param("iamTenantId") Long iamTenantId) {
        return this.selectList(new LambdaQueryWrapper<IamTenantAuthority>().eq(IamTenantAuthority::getIamTenantId, iamTenantId));
    }

    default List<IamTenantAuthority> selectByIamPermissionId(@Param("iamPermissionId") Long iamPermissionId) {
        return this.selectList(new LambdaQueryWrapper<IamTenantAuthority>().eq(IamTenantAuthority::getIamPermissionId, iamPermissionId));
    }

}
