package plus.wcj.heifer.common.mybatis.crud;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/12/31
 */
public interface UpdateMapper<T, ID extends Serializable> {

    /**
     * 根据 ID 修改
     *
     * @param entity 实体对象
     */
    int updateById(T entity);

    /**
     * 根据 whereEntity 条件，更新记录
     *
     * @param entity 实体对象 (set 条件值,可以为 null)
     * @param idList  主键ID列表(不能为 null 以及 empty)
     */
    int updateByIds(T entity, Collection<ID> idList);

    /**
     * 根据 whereEntity 条件，更新记录
     *
     * @param entity 实体对象 (set 条件值,可以为 null)
     * @param updateEntity 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句）
     */
    int update(T entity, T updateEntity);
}
