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

package plus.wcj.heifer.plugin.iam.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import plus.wcj.heifer.plugin.iam.entity.IamTenantStrategyAuthority;

import java.util.List;

/**
 * <p>
 * 租户配置的策略规则与功能权限的绑定 Mapper 接口
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-06-26
 */
public interface IamTenantStrategyAuthorityDao extends BaseMapper<IamTenantStrategyAuthority> {
     // 自动生成外键查询  请勿修改
    default List<IamTenantStrategyAuthority> selectByIamTenantStrategyId(@Param("iamTenantStrategyId") Long iamTenantStrategyId){
        return this.selectList(new LambdaQueryWrapper<IamTenantStrategyAuthority>().eq(IamTenantStrategyAuthority::getIamTenantStrategyId, iamTenantStrategyId));
    }

    default List<IamTenantStrategyAuthority> selectByIamTenantId(@Param("iamTenantId") Long iamTenantId){
        return this.selectList(new LambdaQueryWrapper<IamTenantStrategyAuthority>().eq(IamTenantStrategyAuthority::getIamTenantId, iamTenantId));
    }

    default List<IamTenantStrategyAuthority> selectByIamPermissionId(@Param("iamPermissionId") Long iamPermissionId){
        return this.selectList(new LambdaQueryWrapper<IamTenantStrategyAuthority>().eq(IamTenantStrategyAuthority::getIamPermissionId, iamPermissionId));
    }

    // 自动生成外键查询  请勿修改
}
