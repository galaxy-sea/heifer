package plus.wcj.heifer.plugin.iam.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.plugin.iam.entity.IamDept;

import java.util.List;

/**
 * <p>
 * 部门 Mapper 接口
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-04-23
 */
public interface IamDeptDao extends BaseMapper<IamDept> {

    default List<IamDept> selectByParentId(@Param("parentId") Long parentId) {
        return this.selectList(new LambdaQueryWrapper<IamDept>().eq(IamDept::getParentId, parentId));
    }

    default List<IamDept> selectByIamTenantId(@Param("iamTenantId") Long iamTenantId) {
        return this.selectList(new LambdaQueryWrapper<IamDept>().eq(IamDept::getIamTenantId, iamTenantId));
    }

}
