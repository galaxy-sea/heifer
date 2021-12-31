package plus.wcj.heifer.common.mybatis.crud;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/12/31
 */
public interface DeleteMapper<T, ID extends Serializable> {

    /**
     * 根据 ID 删除
     *
     * @param id 主键ID
     */
    int deleteById(ID id);

    /**
     * 删除（根据ID 批量删除）
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    int deleteByIds(Collection<ID> idList);


    /**
     * 根据 entity 条件，删除记录
     *
     * @param query 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
     */
    int delete(T query);

    /**
     * 根据 columnMap 条件，删除记录
     *
     * @param columnMap 表字段 map 对象
     */
    int deleteByMap(Map<String, Object> columnMap);



}
