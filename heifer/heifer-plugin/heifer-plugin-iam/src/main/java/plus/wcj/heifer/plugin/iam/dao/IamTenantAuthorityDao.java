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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.plugin.iam.entity.IamTenantAuthority;

import java.util.List;

/**
 * <p>
 * 租户拥有的权限 Mapper 接口
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-04-23
 */
public interface IamTenantAuthorityDao extends BaseMapper<IamTenantAuthority> {

    default List<IamTenantAuthority> selectByIamTenantId(@Param("iamTenantId") Long iamTenantId) {
        return this.selectList(new LambdaQueryWrapper<IamTenantAuthority>().eq(IamTenantAuthority::getIamTenantId, iamTenantId));
    }

    default List<IamTenantAuthority> selectByIamPermissionId(@Param("iamPermissionId") Long iamPermissionId) {
        return this.selectList(new LambdaQueryWrapper<IamTenantAuthority>().eq(IamTenantAuthority::getIamPermissionId, iamPermissionId));
    }

}
