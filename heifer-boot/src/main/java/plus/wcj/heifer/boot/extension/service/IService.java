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
package plus.wcj.heifer.boot.extension.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 顶级 Service
 *
 * @author hubin
 * @since 2018-06-23
 */
public interface IService<T> {

    /**
     * 默认批次提交数量
     */
    int DEFAULT_BATCH_SIZE = 1000;

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
    boolean saveBatch(Collection<T> entityList);

    /**
     * 插入（批量）
     *
     * @param entityList 实体对象集合
     * @param batchSize 插入批次数量
     */
    boolean saveBatch(Collection<T> entityList, int batchSize);

    /**
     * 批量修改插入
     *
     * @param entityList 实体对象集合
     */
    boolean saveOrUpdateBatch(Collection<T> entityList);

    /**
     * 批量修改插入
     *
     * @param entityList 实体对象集合
     * @param batchSize 每次的数量
     */
    boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize);

    /**
     * 根据 ID 删除
     *
     * @param id 主键ID
     */
    boolean removeById(Serializable id);

    /**
     * 根据 columnMap 条件，删除记录
     *
     * @param columnMap 表字段 map 对象
     */
    boolean removeByMap(Map<String, Object> columnMap);

    /**
     * 删除（根据ID 批量删除）
     *
     * @param idList 主键ID列表
     */
    boolean removeByIds(Collection<? extends Serializable> idList);

    /**
     * 根据 ID 选择修改
     *
     * @param entity 实体对象
     */
    boolean updateById(T entity);

    /**
     * 根据ID 批量更新
     *
     * @param entityList 实体对象集合
     */
    boolean updateBatchById(Collection<T> entityList);

    /**
     * 根据ID 批量更新
     *
     * @param entityList 实体对象集合
     * @param batchSize 更新批次数量
     */
    boolean updateBatchById(Collection<T> entityList, int batchSize);

    /**
     * TableId 注解存在更新记录，否插入一条记录
     *
     * @param entity 实体对象
     */
    boolean saveOrUpdate(T entity);

    /**
     * 根据 ID 查询
     *
     * @param id 主键ID
     */
    T getById(Serializable id);

    /**
     * 查询（根据ID 批量查询）
     *
     * @param idList 主键ID列表
     */
    List<T> listByIds(Collection<? extends Serializable> idList);

    /**
     * 查询（根据 columnMap 条件）
     *
     * @param columnMap 表字段 map 对象
     */
    List<T> listByMap(Map<String, Object> columnMap);

    /**
     * 查询总记录数
     *
     * @see com.baomidou.mybatisplus.core.toolkit.Wrappers#emptyWrapper()
     */
    int count();

    /**
     * 查询所有
     *
     * @see com.baomidou.mybatisplus.core.toolkit.Wrappers#emptyWrapper()
     */
    List<T> list();

    /**
     * 无条件翻页查询
     *
     * @param page 翻页对象
     *
     * @see com.baomidou.mybatisplus.core.toolkit.Wrappers#emptyWrapper()
     */
    <E extends IPage<T>> E page(E page);

    /**
     * 查询所有列表
     *
     * @see com.baomidou.mybatisplus.core.toolkit.Wrappers#emptyWrapper()
     */
    List<Map<String, Object>> listMaps();

    /**
     * 查询全部记录
     */
    List<Object> listObjs();

    /**
     * 查询全部记录
     *
     * @param mapper 转换函数
     */
    <V> List<V> listObjs(Function<? super Object, V> mapper);

    /**
     * 无条件翻页查询
     *
     * @param page 翻页对象
     *
     * @see com.baomidou.mybatisplus.core.toolkit.Wrappers#emptyWrapper()
     */
    <E extends IPage<Map<String, Object>>> E pageMaps(E page);

    /**
     * 获取 entity 的 class
     *
     * @return {@link Class<T>}
     */
    Class<T> getEntityClass();
}
