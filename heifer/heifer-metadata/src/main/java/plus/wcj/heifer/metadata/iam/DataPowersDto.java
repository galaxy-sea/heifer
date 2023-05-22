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

import java.util.ArrayList;
import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/3/13
 */
public class DataPowersDto {

    /**
     * 所在部门
     */
    private Long deptId;

    /**
     * 拥有的数据权限
     */
    private List<Long> dataPowers;
    /**
     * 是否拥有tenant的全部dept权限，超级管理员权限
     */
    private Boolean tenantDataPower;

    /**
     * 是否拥有当前tenant下的全部dept权限，租户管理员权限
     */
    private Boolean deptDataPower;


    public static DataPowersDto init(DataPowersDto dataPowersDto) {
        if (dataPowersDto == null) {
            dataPowersDto = new DataPowersDto();
        }
        if (dataPowersDto.getDataPowers() == null) {
            dataPowersDto.setDataPowers(new ArrayList<>());
        }
        if (dataPowersDto.getDeptDataPower() == null) {
            dataPowersDto.setDeptDataPower(Boolean.FALSE);
        }
        if (dataPowersDto.getTenantDataPower() == null) {
            dataPowersDto.setTenantDataPower(Boolean.FALSE);
        }
        return dataPowersDto;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public List<Long> getDataPowers() {
        return dataPowers;
    }

    public void setDataPowers(List<Long> dataPowers) {
        this.dataPowers = dataPowers;
    }

    public Boolean getTenantDataPower() {
        return tenantDataPower;
    }

    public void setTenantDataPower(Boolean tenantDataPower) {
        this.tenantDataPower = tenantDataPower;
    }

    public Boolean getDeptDataPower() {
        return deptDataPower;
    }

    public void setDeptDataPower(Boolean deptDataPower) {
        this.deptDataPower = deptDataPower;
    }
}
