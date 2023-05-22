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

package plus.wcj.heifer.metadata.iam;

import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/6/28
 */
public class User {
    /** 用户id */
    private final Long accountId;

    /** 用户id */
    private final String username;

    /** 组织id */
    private final Long tenantId;

    /** 部门id */
    private final Long deptId;

    /** 数据权限 */
    private final List<Long> dataPowers;
    /** 是否拥有tenant的全部dept权限，超级管理员权限 */
    private final boolean tenantDataPower;
    /** 是否拥有当前tenant下的全部dept权限，租户管理员权限 */
    private final boolean deptDataPower;

    public User(Long accountId, String username, Long tenantId, Long deptId, List<Long> dataPowers, boolean tenantDataPower, boolean deptDataPower) {
        this.accountId = accountId;
        this.username = username;
        this.tenantId = tenantId;
        this.deptId = deptId;
        this.dataPowers = dataPowers;
        this.tenantDataPower = tenantDataPower;
        this.deptDataPower = deptDataPower;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getUsername() {
        return username;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public List<Long> getDataPowers() {
        return dataPowers;
    }

    public boolean isTenantDataPower() {
        return tenantDataPower;
    }

    public boolean isDeptDataPower() {
        return deptDataPower;
    }
}
