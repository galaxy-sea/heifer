/*
 * Copyright (c) 2011-2020, baomidou (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package plus.wcj.heifer.boot.extension.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import plus.wcj.heifer.boot.extension.service.IService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * IService 实现类（ 泛型：M 是 mapper 对象，T 是实体 ）
 *
 * @author hubin
 * @since 2018-06-23
 */
public class ServiceImpl<M extends BaseMapper<T>, T> implements IService<T> {

    protected Log log = LogFactory.getLog(getClass());

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected M baseMapper;

    public M getBaseMapper() {
        return baseMapper;
    }

    protected Class<T> entityClass = currentModelClass();

    protected Class<T> mapperClass = currentMapperClass();

    @Override
    public Class<T> getEntityClass() {
        return entityClass;
    }




    /**
     * 判断数据库操作是否成功
     *
     * @param result 数据库操作返回影响条数
     *
     * @return boolean
     *
     * @deprecated 3.3.1
     */
    @Deprecated
    protected boolean retBool(Integer result) {
        return SqlHelper.retBool(result);
    }

    protected Class<T> currentMapperClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(getClass(), 0);
    }

    protected Class<T> currentModelClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(getClass(), 1);
    }

    /**
     * 批量操作 SqlSession
     *
     * @deprecated 3.3.0
     */
    @Deprecated
    protected SqlSession sqlSessionBatch() {
        return SqlHelper.sqlSessionBatch(entityClass);
    }

    /**
     * 释放sqlSession
     *
     * @param sqlSession session
     *
     * @deprecated 3.3.0
     */
    @Deprecated
    protected void closeSqlSession(SqlSession sqlSession) {
        SqlSessionUtils.closeSqlSession(sqlSession, GlobalConfigUtils.currentSessionFactory(entityClass));
    }

    /**
     * 获取 SqlStatement
     *
     * @param sqlMethod ignore
     *
     * @return ignore
     *
     * @see #getSqlStatement(com.baomidou.mybatisplus.core.enums.SqlMethod)
     * @deprecated 3.4.0
     */
    @Deprecated
    protected String sqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.table(entityClass).getSqlStatement(sqlMethod.getMethod());
    }

    @Override
    public boolean save(T entity) {
        return SqlHelper.retBool(getBaseMapper().insert(entity));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Collection<T> entityList) {
        return save(entityList, DEFAULT_BATCH_SIZE);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Collection<T> entityList, int batchSize) {
        String sqlStatement = getSqlStatement(SqlMethod.INSERT_ONE);
        return executeBatch(entityList, batchSize, (sqlSession, entity) -> sqlSession.insert(sqlStatement, entity));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdate(Collection<T> entityList) {
        return saveOrUpdate(entityList, DEFAULT_BATCH_SIZE);
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


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdate(T entity) {
        if (null != entity) {
            TableInfo tableInfo = TableInfoHelper.getTableInfo(this.entityClass);
            Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
            String keyProperty = tableInfo.getKeyProperty();
            Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
            Object idVal = ReflectionKit.getFieldValue(entity, tableInfo.getKeyProperty());
            return StringUtils.checkValNull(idVal) || Objects.isNull(get((Serializable) idVal)) ? save(entity) : updateById(entity);
        }
        return false;
    }

    @Override
    public T get(T entity) {
        return getBaseMapper().selectOne(new QueryWrapper<>(entity));
    }

    @Override
    public T get(Serializable id) {
        return getBaseMapper().selectById(id);
    }

    @Override
    public List<T> list(Collection<? extends Serializable> idList) {
        return getBaseMapper().selectBatchIds(idList);
    }

    @Override
    public List<T> list(Map<String, Object> columnMap) {
        return getBaseMapper().selectByMap(columnMap);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
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
    public boolean remove(Serializable id) {
        return SqlHelper.retBool(getBaseMapper().deleteById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean remove(Map<String, Object> columnMap) {
        return SqlHelper.retBool(getBaseMapper().deleteByMap(columnMap));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean remove(Collection<? extends Serializable> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return false;
        }
        return SqlHelper.retBool(getBaseMapper().deleteBatchIds(idList));
    }

    @Override
    public boolean updateById(T entity) {
        return SqlHelper.retBool(getBaseMapper().updateById(entity));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(Collection<T> entityList) {
        return updateById(entityList, DEFAULT_BATCH_SIZE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(Collection<T> entityList, int batchSize) {
        String sqlStatement = getSqlStatement(SqlMethod.UPDATE_BY_ID);
        return executeBatch(entityList, batchSize, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
            param.put(Constants.ENTITY, entity);
            sqlSession.update(sqlStatement, param);
        });
    }


    @Override
    public int count() {
        return SqlHelper.retCount(getBaseMapper().selectCount(Wrappers.emptyWrapper()));
    }


    public int count(T entity) {
        return SqlHelper.retCount(getBaseMapper().selectCount(new QueryWrapper<>(entity)));
    }

    @Override
    public List<T> list() {
        return getBaseMapper().selectList(Wrappers.emptyWrapper());
    }

    @Override
    public <E extends IPage<T>> E page(E page) {
        return getBaseMapper().selectPage(page, Wrappers.emptyWrapper());
    }

    @Override
    public <E extends IPage<T>> E page(E page, T entity) {
        return getBaseMapper().selectPage(page, new QueryWrapper<>(entity));
    }

    @Override
    public List<Map<String, Object>> listMaps() {
        return getBaseMapper().selectMaps(Wrappers.emptyWrapper());
    }

    @Override
    public List<Object> listObjs() {
        return listObjs(Function.identity());
    }

    @Override
    public <V> List<V> listObjs(Function<? super Object, V> mapper) {
        return getBaseMapper().selectObjs(Wrappers.emptyWrapper()).stream().filter(Objects::nonNull).map(mapper).collect(Collectors.toList());
    }

    @Override
    public <E extends IPage<Map<String, Object>>> E pageMaps(E page) {
        return getBaseMapper().selectMapsPage(page, Wrappers.emptyWrapper());
    }

    /**
     * 执行批量操作
     *
     * @param consumer consumer
     *
     * @since 3.3.0
     * @deprecated 3.3.1 后面我打算移除掉 {@link #executeBatch(java.util.Collection, int, java.util.function.BiConsumer)} }.
     */
    @Deprecated
    protected boolean executeBatch(Consumer<SqlSession> consumer) {
        return SqlHelper.executeBatch(this.entityClass, this.log, consumer);
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
