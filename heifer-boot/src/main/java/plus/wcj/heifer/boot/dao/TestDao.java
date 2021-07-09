package plus.wcj.heifer.boot.dao;

import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.boot.extension.tenant.Tenant;

import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/7/8
 */
public interface TestDao {

    List<Long> test1(@Param("tenant") Tenant tenant);

}
