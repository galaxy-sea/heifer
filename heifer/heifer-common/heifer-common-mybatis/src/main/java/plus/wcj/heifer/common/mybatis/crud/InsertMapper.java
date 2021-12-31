package plus.wcj.heifer.common.mybatis.crud;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/12/31
 */
public interface InsertMapper<T, ID extends Serializable> {

    /**
     * 插入一条记录
     *
     * @param entity 实体对象
     */
    int insert(T entity);

    /**
     * 插入一条记录
     *
     * @param entityList 实体对象
     */
    int insert(Collection<T> entityList);


    /**
     * 插入一条记录
     *
     * @param entityList 实体对象
     */
    int insert(Collection<T> entityList, int batchSize);


}
