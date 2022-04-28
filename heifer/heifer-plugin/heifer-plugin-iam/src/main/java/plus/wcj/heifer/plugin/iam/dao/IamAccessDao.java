package plus.wcj.heifer.plugin.iam.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.plugin.iam.entity.IamAccess;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-04-23
 */
public interface IamAccessDao extends BaseMapper<IamAccess> {

    default List<IamAccess> selectByIamAccountManageId(@Param("iamAccountManageId") Long iamAccountManageId) {
        return this.selectList(new LambdaQueryWrapper<IamAccess>().eq(IamAccess::getIamAccountManageId, iamAccountManageId));
    }

    default List<IamAccess> selectByAccessKeyId(@Param("accessKeyId") String accessKeyId) {
        return this.selectList(new LambdaQueryWrapper<IamAccess>().eq(IamAccess::getAccessKeyId, accessKeyId));
    }

}
