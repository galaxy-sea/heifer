/*
 * Copyright 2021-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package plus.wcj.heifer.common.mybatisplus.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionUtils;
import plus.wcj.heifer.common.mybatisplus.IService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * The IOUtils. copy from Maven: com.baomidou:mybatis-plus-extension 3.5.2
 *
 * @param <T> entity类
 * @param <ID> id主键类型
 * @param <M> mybatis dao
 *
 * @author changjin wei(魏昌进)
 * @since 2021/10/25
 */
@SuppressWarnings("AlibabaCommentsMustBeJavadocFormat")
public class ServiceImpl<M extends BaseMapper<T>, T, ID extends Serializable> implements IService<T, ID> {

        protected Log log = LogFactory.getLog(getClass());

        @Autowired
        protected M baseMapper;

        @Override
        public M getBaseMapper() {
        return baseMapper;
    }

        protected Class<T> entityClass = currentModelClass();

        @Override
        public Class<T> getEntityClass() {
        return entityClass;
    }

        protected Class<M> mapperClass = currentMapperClass();

        /**
         * 判断数据库操作是否成功
         *
         * @param result 数据库操作返回影响条数
         * @return boolean
         * @deprecated 3.3.1
         */
        @Deprecated
        protected boolean retBool(Integer result) {
        return SqlHelper.retBool(result);
    }

        protected Class<M> currentMapperClass() {
        return (Class<M>) ReflectionKit.getSuperClassGenericType(this.getClass(), com.baomidou.mybatisplus.extension.service.impl.ServiceImpl.class, 0);
    }

        protected Class<T> currentModelClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(this.getClass(), com.baomidou.mybatisplus.extension.service.impl.ServiceImpl.class, 1);
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
         * @return ignore
         * @see #getSqlStatement(SqlMethod)
         * @deprecated 3.4.0
         */
        @Deprecated
        protected String sqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.table(entityClass).getSqlStatement(sqlMethod.getMethod());
    }

        /**
         * 批量插入
         *
         * @param entityList ignore
         * @param batchSize  ignore
         * @return ignore
         */
        @Transactional(rollbackFor = Exception.class)
        @Override
        public boolean saveBatch(Collection<T> entityList, int batchSize) {
        String sqlStatement = getSqlStatement(SqlMethod.INSERT_ONE);
        return executeBatch(entityList, batchSize, (sqlSession, entity) -> sqlSession.insert(sqlStatement, entity));
    }

        /**
         * 获取mapperStatementId
         *
         * @param sqlMethod 方法名
         * @return 命名id
         * @since 3.4.0
         */
        protected String getSqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.getSqlStatement(mapperClass, sqlMethod);
    }

        /**
         * TableId 注解存在更新记录，否插入一条记录
         *
         * @param entity 实体对象
         * @return boolean
         */
        @Transactional(rollbackFor = Exception.class)
        @Override
        public boolean saveOrUpdate(T entity) {
        if (null != entity) {
            TableInfo tableInfo = TableInfoHelper.getTableInfo(this.entityClass);
            Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
            String keyProperty = tableInfo.getKeyProperty();
            Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
            Object idVal = tableInfo.getPropertyValue(entity, tableInfo.getKeyProperty());
            return StringUtils.checkValNull(idVal) || Objects.isNull(getById((Serializable) idVal)) ? save(entity) : updateById(entity);
        }
        return false;
    }

        @Transactional(rollbackFor = Exception.class)
        @Override
        public boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entityClass);
        Assert.notNull(tableInfo, "error: can not execute. because can not find cache of TableInfo for entity!");
        String keyProperty = tableInfo.getKeyProperty();
        Assert.notEmpty(keyProperty, "error: can not execute. because can not find column for id from entity!");
        return SqlHelper.saveOrUpdateBatch(this.entityClass, this.mapperClass, this.log, entityList, batchSize, (sqlSession, entity) -> {
            Object idVal = tableInfo.getPropertyValue(entity, keyProperty);
            return StringUtils.checkValNull(idVal)
                    || CollectionUtils.isEmpty(sqlSession.selectList(getSqlStatement(SqlMethod.SELECT_BY_ID), entity));
        }, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
            param.put(Constants.ENTITY, entity);
            sqlSession.update(getSqlStatement(SqlMethod.UPDATE_BY_ID), param);
        });
    }

        @Transactional(rollbackFor = Exception.class)
        @Override
        public boolean updateBatchById(Collection<T> entityList, int batchSize) {
        String sqlStatement = getSqlStatement(SqlMethod.UPDATE_BY_ID);
        return executeBatch(entityList, batchSize, (sqlSession, entity) -> {
            MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
            param.put(Constants.ENTITY, entity);
            sqlSession.update(sqlStatement, param);
        });
    }

        @Override
        public T getOne(Wrapper<T> queryWrapper, boolean throwEx) {
        if (throwEx) {
            return baseMapper.selectOne(queryWrapper);
        }
        return SqlHelper.getObject(log, baseMapper.selectList(queryWrapper));
    }

        @Override
        public Map<String, Object> getMap(Wrapper<T> queryWrapper) {
        return SqlHelper.getObject(log, baseMapper.selectMaps(queryWrapper));
    }

        @Override
        public <V> V getObj(Wrapper<T> queryWrapper, Function<? super Object, V> mapper) {
        return SqlHelper.getObject(log, listObjs(queryWrapper, mapper));
    }

        /**
         * 执行批量操作
         *
         * @param consumer consumer
         * @since 3.3.0
         * @deprecated 3.3.1 后面我打算移除掉 {@link #executeBatch(Collection, int, BiConsumer)} }.
         */
        @Deprecated
        protected boolean executeBatch(Consumer<SqlSession> consumer) {
        return SqlHelper.executeBatch(this.entityClass, this.log, consumer);
    }

        /**
         * 执行批量操作
         *
         * @param list      数据集合
         * @param batchSize 批量大小
         * @param consumer  执行方法
         * @param <E>       泛型
         * @return 操作结果
         * @since 3.3.1
         */
        protected <E> boolean executeBatch(Collection<E> list, int batchSize, BiConsumer<SqlSession, E> consumer) {
        return SqlHelper.executeBatch(this.entityClass, this.log, list, batchSize, consumer);
    }

        /**
         * 执行批量操作（默认批次提交数量{@link com.baomidou.mybatisplus.extension.service.IService#DEFAULT_BATCH_SIZE}）
         *
         * @param list     数据集合
         * @param consumer 执行方法
         * @param <E>      泛型
         * @return 操作结果
         * @since 3.3.1
         */
        protected <E> boolean executeBatch(Collection<E> list, BiConsumer<SqlSession, E> consumer) {
        return executeBatch(list, DEFAULT_BATCH_SIZE, consumer);
    }

        @Override
        public boolean removeById(Serializable id) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(getEntityClass());
        if (tableInfo.isWithLogicDelete() && tableInfo.isWithUpdateFill()) {
            return removeById(id, true);
        }
        return SqlHelper.retBool(getBaseMapper().deleteById(id));
    }

        @Override
        @Transactional(rollbackFor = Exception.class)
        public boolean removeByIds(Collection<?> list) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        TableInfo tableInfo = TableInfoHelper.getTableInfo(getEntityClass());
        if (tableInfo.isWithLogicDelete() && tableInfo.isWithUpdateFill()) {
            return removeBatchByIds(list, true);
        }
        return SqlHelper.retBool(getBaseMapper().deleteBatchIds(list));
    }

        @Override
        public boolean removeById(Serializable id, boolean useFill) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entityClass);
        if (useFill && tableInfo.isWithLogicDelete()) {
            if (!entityClass.isAssignableFrom(id.getClass())) {
                T instance = tableInfo.newInstance();
                tableInfo.setPropertyValue(instance, tableInfo.getKeyProperty(), id);
                return removeById(instance);
            }
        }
        return SqlHelper.retBool(getBaseMapper().deleteById(id));
    }

        @Override
        @Transactional(rollbackFor = Exception.class)
        public boolean removeBatchByIds(Collection<?> list, int batchSize) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entityClass);
        return removeBatchByIds(list, batchSize, tableInfo.isWithLogicDelete() && tableInfo.isWithUpdateFill());
    }

        @Override
        @Transactional(rollbackFor = Exception.class)
        public boolean removeBatchByIds(Collection<?> list, int batchSize, boolean useFill) {
        String sqlStatement = getSqlStatement(SqlMethod.DELETE_BY_ID);
        TableInfo tableInfo = TableInfoHelper.getTableInfo(entityClass);
        return executeBatch(list, batchSize, (sqlSession, e) -> {
            if (useFill && tableInfo.isWithLogicDelete()) {
                if (entityClass.isAssignableFrom(e.getClass())) {
                    sqlSession.update(sqlStatement, e);
                } else {
                    T instance = tableInfo.newInstance();
                    tableInfo.setPropertyValue(instance, tableInfo.getKeyProperty(), e);
                    sqlSession.update(sqlStatement, instance);
                }
            } else {
                sqlSession.update(sqlStatement, e);
            }
        });
    }

    }
