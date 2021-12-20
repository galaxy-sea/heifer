package plus.wcj.heifer.boot.mybatisplus;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * @param <T> entity类
 * @param <ID> id主键类型
 *
 * @author changjin wei(魏昌进)
 * @since 2021/10/25
 */
@SuppressWarnings("unused")
public interface IService<T, ID extends Serializable> {

    /**
     * 默认批次提交数量
     */
    int DEFAULT_BATCH_SIZE = 1000;


    /**
     * 查询总记录数
     */
    int count();

    /**
     * 根据 Wrapper 条件，查询总记录数
     *
     * @param queryEntity 实体对象封装操作类
     */
    int count(T queryEntity);


    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    T get(ID id);


    /**
     * 根据 Wrapper，查询一条记录
     *
     * @param queryEntity 实体对象封装操作类
     */
    Map<String, Object> getMap(T queryEntity);


    /**
     * 根据 Wrapper，查询一条记录 <br/>
     * <p>结果集，如果是多个会抛出异常，随机取一条加上限制条件 wrapper.last("LIMIT 1")</p>
     *
     * @param queryEntity 实体对象封装操作类
     */
    T get(T queryEntity);

    /**
     * 根据 Wrapper，查询一条记录
     *
     * @param queryEntity 实体对象封装操作类
     * @param throwEx 有多个 result 是否抛出异常
     */
    T get(T queryEntity, boolean throwEx);


    /**
     * 查询所有
     *
     * @see com.baomidou.mybatisplus.core.toolkit.Wrappers#emptyWrapper()
     */
    List<T> list();

    /**
     * 查询列表
     *
     * @param queryEntity 实体对象封装操作类
     */
    List<T> list(T queryEntity);

    /**
     * 查询（根据ID 批量查询）
     *
     * @param idList 主键ID列表
     */
    List<T> list(Collection<? extends ID> idList);

    /**
     * 查询（根据 columnMap 条件）
     *
     * @param columnMap 表字段 map 对象
     */
    List<T> list(Map<String, Object> columnMap);


    /**
     * 查询所有列表
     *
     * @see com.baomidou.mybatisplus.core.toolkit.Wrappers#emptyWrapper()
     */
    List<Map<String, Object>> listMaps();


    /**
     * 查询列表
     *
     * @param queryEntity 实体对象封装操作类
     */
    List<Map<String, Object>> listMaps(T queryEntity);


    /**
     * 查询全部记录
     */
    List<Object> listObjs();

    /**
     * 根据 Wrapper 条件，查询全部记录
     *
     * @param queryEntity 实体对象封装操作类
     */
    List<Object> listObjs(T queryEntity);

    /**
     * 无条件翻页查询
     *
     * @param page 翻页对象
     *
     * @see com.baomidou.mybatisplus.core.toolkit.Wrappers#emptyWrapper()
     */
    <E extends IPage<T>> E page(E page);

    /**
     * 翻页查询
     *
     * @param page 翻页对象
     * @param queryEntity 实体对象封装操作类
     */
    <E extends IPage<T>> E page(E page, T queryEntity);

    /**
     * 无条件翻页查询
     *
     * @param page 翻页对象
     *
     * @see com.baomidou.mybatisplus.core.toolkit.Wrappers#emptyWrapper()
     */
    <E extends IPage<Map<String, Object>>> E pageMaps(E page);

    /**
     * 翻页查询
     *
     * @param page 翻页对象
     * @param queryEntity 实体对象封装操作类
     */
    <E extends IPage<Map<String, Object>>> E pageMaps(E page, T queryEntity);


    /**
     * 根据 queryEntity 条件，删除记录
     *
     * @param queryEntity 实体类
     */
    boolean remove(T queryEntity);

    /**
     * 根据 ID 删除
     *
     * @param id 主键ID
     */
    boolean remove(ID id);


    /**
     * 删除（根据ID 批量删除）
     *
     * @param idList 主键ID列表
     */
    boolean remove(Collection<? extends ID> idList);


    /**
     * 根据 columnMap 条件，删除记录
     *
     * @param columnMap 表字段 map 对象
     */
    boolean remove(Map<String, Object> columnMap);


    /**
     * 插入一条记录（选择字段，策略插入）
     *
     * @param entity 实体对象
     */
    boolean save(T entity);

    /**
     * 插入（批量）
     *
     * @param entityList 实体对象集合
     */
    boolean save(Collection<T> entityList);

    /**
     * 插入（批量）
     *
     * @param entityList 实体对象集合
     * @param batchSize 插入批次数量
     */
    boolean save(Collection<T> entityList, int batchSize);


    /**
     * 批量修改插入
     *
     * @param entityList 实体对象集合
     */
    boolean saveOrUpdate(Collection<T> entityList);

    /**
     * 批量修改插入
     *
     * @param entityList 实体对象集合
     * @param batchSize 每次的数量
     */
    boolean saveOrUpdate(Collection<T> entityList, int batchSize);


    /**
     * 根据 ID 选择修改
     *
     * @param entity 实体对象
     */
    boolean update(T entity);


    /**
     * 根据 updateEntity 条件，更新记录
     *
     * @param entity 实体对象
     * @param updateEntity 实体对象封装操作类
     */
    boolean update(T entity, T updateEntity);

    /**
     * 根据ID 批量更新
     *
     * @param entityList 实体对象集合
     */

    boolean update(Collection<T> entityList);

    /**
     * 根据ID 批量更新
     *
     * @param entityList 实体对象集合
     * @param batchSize 更新批次数量
     */
    boolean update(Collection<T> entityList, int batchSize);

    /**
     * TableId 注解存在更新记录，否插入一条记录
     *
     * @param entity 实体对象
     */
    boolean saveOrUpdate(T entity);


    /**
     * <p>
     * 根据updateWrapper尝试更新，否继续执行saveOrUpdate(T)方法
     * 此次修改主要是减少了此项业务代码的代码量（存在性验证之后的saveOrUpdate操作）
     * </p>
     *
     * @param entity 实体对象
     */
    boolean saveOrUpdate(T entity, T updateEntity);
}
