/*
 * Copyright 2021-2023 the original author or authors.
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

import org.apache.ibatis.annotations.Param;
import plus.wcj.heifer.plugin.iam.dto.AccountDto;
import plus.wcj.heifer.plugin.iam.dto.TenantDto;

import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/04/28
 */
public interface AuthDao {

    /**
     * 获取用户名和密码
     *
     * @param phone 手机号
     *
     * @return 账户
     */
    AccountDto selectAccountByPhone(@Param("phone") String phone);

    /**
     * 获取当前accountId的全部租户信息
     *
     * @param accountId 账户信息
     *
     * @return 租户列表
     */
    List<TenantDto> selectAllTenant(@Param("accountId") String accountId);

    /**
     * 获取账户ID在租户中的全部数据权限
     *
     * @param accountId 账户id
     * @param tenantId 租户id
     *
     * @return dept id列表
     */
    List<Long> listPower(@Param("accountId") Long accountId, @Param("tenantId") Long tenantId);

    /**
     * 获取当前账户在租户中的部门
     *
     * @param accountId 账户id
     * @param tenantId 租户id
     *
     * @return 部门id
     */
    Long getDept(@Param("accountId") Long accountId, @Param("tenantId") Long tenantId);

    /**
     * 获取当前租户下的账户绑定的角色
     *
     * @param iamAccountId 账户id
     * @param iamTenantId  租户id
     * @return 角色名称
     */
    List<String> selectByIamIamAccountIdAndTenantId(@Param("iamAccountId") Long iamAccountId, @Param("iamTenantId") Long iamTenantId);


    /**
     * 获取当前租户下的账户绑定的ACL权限
     *
     * @param iamAccountId 账户id
     * @param iamTenantId  租户id
     * @return permission表达式
     */
    List<String> selectAclPermissionByIamIamAccountIdAndTenantId(@Param("iamAccountId") Long iamAccountId, @Param("iamTenantId") Long iamTenantId);

    /**
     * 获取当前租户下的账户绑定的RBAC权限
     *
     * @param iamAccountId 账户id
     * @param iamTenantId  租户id
     * @return permission表达式
     */
    List<String> selectRbacPermissionByIamIamAccountIdAndTenantId(@Param("iamAccountId") Long iamAccountId, @Param("iamTenantId") Long iamTenantId);
}
