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
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import plus.wcj.heifer.boot.extension.service.IService;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;

/**
 * IService 实现类（ 泛型：M 是 mapper 对象，T 是实体 ）
 *
 * @author hubin
 * @since 2018-06-23
 */
public class ServiceImpl<M extends BaseMapper<T>, T> implements IService<T> {

    protected final Log log = LogFactory.getLog(this.getClass());

    @SuppressWarnings("unchecked")
    private final Class<T> entityClass = (Class<T>) this.currentModelClass();
    @SuppressWarnings("unchecked")
    private final Class<T> mapperClass = (Class<T>) this.currentMapperClass();
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
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
    public boolean save(T entity) {
        return SqlHelper.retBool(this.getBaseMapper().insert(entity));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Collection<T> entityList) {
        return this.save(entityList, DEFAULT_BATCH_SIZE);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Collection<T> entityList, int batchSize) {
        String sqlStatement = this.getSqlStatement(SqlMethod.INSERT_ONE);
        return this.executeBatch(entityList, batchSize, (sqlSession, entity) -> sqlSession.insert(sqlStatement, entity));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdate(Collection<T> entityList) {
        return this.saveOrUpdate(entityList, DEFAULT_BATCH_SIZE);
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
        return SqlHelper.getSqlStatement(this.mapperClass, sqlMethod);
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
            return StringUtils.checkValNull(idVal) || Objects.isNull(this.get((Serializable) idVal)) ? this.save(entity) : this.updateById(entity);
        }
        return false;
    }

    @Override
    public T get(T entity) {
        return this.getBaseMapper().selectOne(new QueryWrapper<>(entity));
    }

    @Override
    public T get(Serializable id) {
        return this.getBaseMapper().selectById(id);
    }

    @Override
    public List<T> list(Collection<? extends Serializable> idList) {
        return this.getBaseMapper().selectBatchIds(idList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdate(Collection<T> entityList, int batchSize) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(this.entityClass);
        Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
        String keyProperty = tableInfo.getKeyProperty();
        Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
        return SqlHelper.saveOrUpdateBatch(this.entityClass, this.mapperClass, this.log, entityList, batchSize, (sqlSession, entity) -> {
            Object idVal = ReflectionKit.getFieldValue(entity, keyProperty);
            return StringUtils.checkValNull(idVal)
                    || CollectionUtils.isEmpty(sqlSession.selectList(this.getSqlStatement(SqlMethod.SELECT_BY_ID), entity));
        }, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
            param.put(Constants.ENTITY, entity);
            sqlSession.update(this.getSqlStatement(SqlMethod.UPDATE_BY_ID), param);
        });
    }

    @Override
    public boolean remove(Serializable id) {
        return SqlHelper.retBool(this.getBaseMapper().deleteById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean remove(T entity) {
        return SqlHelper.retBool(this.getBaseMapper().delete(new QueryWrapper<>(entity)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean remove(Collection<? extends Serializable> idList) {
        if (CollectionUtils.isEmpty(idList)) {
            return false;
        }
        return SqlHelper.retBool(this.getBaseMapper().deleteBatchIds(idList));
    }

    @Override
    public boolean updateById(T entity) {
        return SqlHelper.retBool(this.getBaseMapper().updateById(entity));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(Collection<T> entityList) {
        return this.updateById(entityList, DEFAULT_BATCH_SIZE);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(Collection<T> entityList, int batchSize) {
        String sqlStatement = this.getSqlStatement(SqlMethod.UPDATE_BY_ID);
        return this.executeBatch(entityList, batchSize, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
            param.put(Constants.ENTITY, entity);
            sqlSession.update(sqlStatement, param);
        });
    }


    @Override
    public int count() {
        return SqlHelper.retCount(this.getBaseMapper().selectCount(Wrappers.emptyWrapper()));
    }


    @Override
    public int count(T entity) {
        return SqlHelper.retCount(this.getBaseMapper().selectCount(new QueryWrapper<>(entity)));
    }

    @Override
    public List<T> list() {
        return this.getBaseMapper().selectList(Wrappers.emptyWrapper());
    }

    @Override
    public List<T> list(T entity) {
        return this.getBaseMapper().selectList(new QueryWrapper<>(entity));
    }

    @Override
    public <E extends IPage<T>> E page(E page) {
        return this.getBaseMapper().selectPage(page, Wrappers.emptyWrapper());
    }

    @Override
    public <E extends IPage<T>> E page(E page, T entity) {
        return this.getBaseMapper().selectPage(page, new QueryWrapper<>(entity));
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
