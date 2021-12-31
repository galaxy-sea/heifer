package plus.wcj.heifer.common.mybatis.crud;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageRowBounds;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/12/31
 */
public interface SelectMapper<T, ID extends Serializable> {

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    T selectById(ID id);

    /**
     * 查询（根据ID 批量查询）
     *
     * @param idList 主键ID列表(不能为 null 以及 empty)
     */
    List<T> selectByIds(Collection<ID> idList);

    /**
     * 查询（根据 columnMap 条件）
     *
     * @param columnMap 表字段 map 对象
     */
    List<T> selectByMap(Map<String, Object> columnMap);

    /**
     * 根据 entity 条件，查询一条记录
     *
     * @param query 实体对象封装操作类（可以为 null）
     */
    T selectOne(T query);

    /**
     * 根据 Wrapper 条件，查询总记录数
     *
     * @param query 实体对象封装操作类（可以为 null）
     */
    Integer selectCount(T query);

    /**
     * 根据 entity 条件，查询全部记录
     *
     * @param query 实体对象封装操作类（可以为 null）
     */
    List<T> selectList(T query);


    /**
     * 根据 entity 条件，查询全部记录
     *
     * @param query 实体对象封装操作类（可以为 null）
     */
    List<T> selectList(PageRowBounds page,T query);


    /**
     * 根据 entity 条件，查询全部记录（并翻页）
     *
     * @param page 分页查询条件（可以为 RowBounds.DEFAULT）
     * @param query 实体对象封装操作类（可以为 null）
     */
    Page<T> selectPage(PageRowBounds page, T query);
}
