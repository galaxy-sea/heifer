package plus.wcj.heifer.plugin.iam.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.plugin.iam.dto.TenantDto;
import plus.wcj.heifer.plugin.iam.entity.IamAccount;

import java.util.List;


/**
 * <p>
 * 账户表 Mapper 接口
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-04-23
 */
public interface IamAccountDao extends BaseMapper<IamAccount> {
}
