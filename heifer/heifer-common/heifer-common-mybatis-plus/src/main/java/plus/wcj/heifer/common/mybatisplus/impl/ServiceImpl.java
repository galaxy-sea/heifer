package plus.wcj.heifer.common.mybatisplus.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import plus.wcj.heifer.common.mybatisplus.IService;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @param <T> entity类
 * @param <ID> id主键类型
 * @param <M> mybatis dao
 *
 * @author changjin wei(魏昌进)
 * @since 2021/10/25
 */
@SuppressWarnings("unused")
public class ServiceImpl<M extends BaseMapper<T>, T, ID extends Serializable> implements IService<T, ID> {
    protected final Log log = LogFactory.getLog(this.getClass());

    @SuppressWarnings("unchecked")
    private final Class<T> entityClass = (Class<T>) this.currentModelClass();
    @SuppressWarnings("unchecked")
    private final Class<T> mapperClass = (Class<T>) this.currentMapperClass();
    @SuppressWarnings({"SpringJavaAutowiredMembersInspection"})
    @Autowired
    private M baseMapper;

    protected M getBaseMapper() {
        return this.baseMapper;
    }

    private Class<?> currentMapperClass() {
        return ReflectionKit.getSuperClassGenericType(this.getClass(), 0);
    }

    private Class<?> currentModelClass() {
        return ReflectionKit.getSuperClassGenericType(this.getClass(), 1);
    }

    @Override
    public int count() {
        return SqlHelper.retCount(this.getBaseMapper().selectCount(Wrappers.emptyWrapper()));
    }

    @Override
    public int count(T queryEntity) {
        return SqlHelper.retCount(this.getBaseMapper().selectCount(Wrappers.query(queryEntity)));
    }

    @Override
    public T get(ID id) {
        return getBaseMapper().selectById(id);
    }

    @Override
    public Map<String, Object> getMap(T queryEntity) {
        return SqlHelper.getObject(log, baseMapper.selectMaps(Wrappers.query(queryEntity)));
    }

    @Override
    public T get(T queryEntity) {
        return get(queryEntity, true);
    }

    @Override
    public T get(T queryEntity, boolean throwEx) {
        if (throwEx) {
            return baseMapper.selectOne(Wrappers.query(queryEntity));
        }
        return SqlHelper.getObject(log, baseMapper.selectList(Wrappers.query(queryEntity)));
    }

    @Override
    public List<T> list() {
        return getBaseMapper().selectList(Wrappers.query(null));
    }

    @Override
    public List<T> list(T queryEntity) {
        return getBaseMapper().selectList(Wrappers.query(queryEntity));
    }

    @Override
    public List<T> list(Collection<? extends ID> idList) {
        return getBaseMapper().selectBatchIds(idList);
    }

    @Override
    public List<T> list(Map<String, Object> columnMap) {
        return getBaseMapper().selectByMap(columnMap);
    }

    @Override
    public List<Map<String, Object>> listMaps() {
        return listMaps(null);
    }

    @Override
    public List<Map<String, Object>> listMaps(T queryEntity) {
        return getBaseMapper().selectMaps(Wrappers.query(queryEntity));
    }

    @Override
    public List<Object> listObjs() {
        return listObjs(Function.identity());
    }

    public <V> List<V> listObjs(Function<? super Object, V> mapper) {
        return listObjs(Wrappers.emptyWrapper(), mapper);
    }

    public <V> List<V> listObjs(Wrapper<T> queryWrapper, Function<? super Object, V> mapper) {
        return getBaseMapper().selectObjs(queryWrapper).stream().filter(Objects::nonNull).map(mapper).collect(Collectors.toList());
    }

    @Override
    public List<Object> listObjs(T queryEntity) {
        return listObjs(Wrappers.query(queryEntity), Function.identity());
    }

    @Override
    public <E extends IPage<T>> E page(E page) {
        return page(page, null);
    }

    @Override
    public <E extends IPage<T>> E page(E page, T queryEntity) {
        return getBaseMapper().selectPage(page, Wrappers.query(queryEntity));
    }

    @Override
    public <E extends IPage<Map<String, Object>>> E pageMaps(E page) {
        return pageMaps(page, null);
    }

    @Override
    public <E extends IPage<Map<String, Object>>> E pageMaps(E page, T queryEntity) {
        return getBaseMapper().selectMapsPage(page, Wrappers.query(queryEntity));
    }

    @Override
    public boolean remove(T queryEntity) {
        return SqlHelper.retBool(getBaseMapper().delete(Wrappers.query(queryEntity)));
    }

    @Override
    public boolean remove(ID id) {
        return SqlHelper.retBool(getBaseMapper().deleteById(id));
    }

    @Override
    public boolean remove(Collection<? extends ID> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return false;
        }
        return SqlHelper.retBool(getBaseMapper().deleteBatchIds(idList));
    }

    @Override
    public boolean remove(Map<String, Object> columnMap) {
        return SqlHelper.retBool(getBaseMapper().deleteByMap(columnMap));
    }

    @Override
    public boolean save(T entity) {
        return SqlHelper.retBool(getBaseMapper().insert(entity));
    }

    @Override
    public boolean save(Collection<T> entityList) {
        return save(entityList, DEFAULT_BATCH_SIZE);
    }

    @Override
    public boolean save(Collection<T> entityList, int batchSize) {
        String sqlStatement = getSqlStatement(SqlMethod.INSERT_ONE);
        return executeBatch(entityList, batchSize, (sqlSession, entity) -> sqlSession.insert(sqlStatement, entity));
    }

    @Override
    public boolean saveOrUpdate(Collection<T> entityList) {
        return saveOrUpdate(entityList, DEFAULT_BATCH_SIZE);
    }

    @Override
    public boolean saveOrUpdate(Collection<T> entityList, int batchSize) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entityClass);
        Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
        String keyProperty = tableInfo.getKeyProperty();
        Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
        return SqlHelper.saveOrUpdateBatch(this.entityClass, this.mapperClass, this.log, entityList, batchSize, (sqlSession, entity) -> {
            Object idVal = ReflectionKit.getFieldValue(entity, keyProperty);
            return StringUtils.checkValNull(idVal)
                    || CollectionUtils.isEmpty(sqlSession.selectList(getSqlStatement(SqlMethod.SELECT_BY_ID), entity));
        }, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
            param.put(Constants.ENTITY, entity);
            sqlSession.update(getSqlStatement(SqlMethod.UPDATE_BY_ID), param);
        });
    }

    @Override
    public boolean update(T entity) {
        return SqlHelper.retBool(getBaseMapper().updateById(entity));
    }

    @Override
    public boolean update(T entity, T updateEntity) {
        return SqlHelper.retBool(getBaseMapper().update(entity, Wrappers.query(updateEntity)));
    }

    @Override
    public boolean update(Collection<T> entityList) {
        return update(entityList, DEFAULT_BATCH_SIZE);
    }

    @Override
    public boolean update(Collection<T> entityList, int batchSize) {
        String sqlStatement = getSqlStatement(SqlMethod.UPDATE_BY_ID);
        return executeBatch(entityList, batchSize, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
            param.put(Constants.ENTITY, entity);
            sqlSession.update(sqlStatement, param);
        });
    }

    @Override
    public boolean saveOrUpdate(T entity) {
        if (null != entity) {
            TableInfo tableInfo = TableInfoHelper.getTableInfo(this.entityClass);
            Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
            String keyProperty = tableInfo.getKeyProperty();
            Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
            Object idVal = ReflectionKit.getFieldValue(entity, tableInfo.getKeyProperty());
            //noinspection unchecked
            return StringUtils.checkValNull(idVal) || Objects.isNull(get((ID) idVal)) ? save(entity) : update(entity);
        }
        return false;
    }

    @Override
    public boolean saveOrUpdate(T entity, T updateEntity) {
        return update(entity, updateEntity) || saveOrUpdate(entity);
    }


    /**
     * 获取mapperStatementId
     *
     * @param sqlMethod 方法名
     *
     * @return 命名id
     *
     * @since 3.4.0
     */
    protected String getSqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.getSqlStatement(mapperClass, sqlMethod);
    }


    /**
     * 执行批量操作
     *
     * @param list 数据集合
     * @param batchSize 批量大小
     * @param consumer 执行方法
     * @param <E> 泛型
     *
     * @return 操作结果
     *
     * @since 3.3.1
     */
    protected <E> boolean executeBatch(Collection<E> list, int batchSize, BiConsumer<SqlSession, E> consumer) {
        return SqlHelper.executeBatch(this.entityClass, this.log, list, batchSize, consumer);
    }

}
