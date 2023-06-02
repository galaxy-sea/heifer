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

package plus.wcj.heifer.plugin.iam.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import plus.wcj.heifer.metadata.annotation.PostValid;
import plus.wcj.heifer.metadata.annotation.PutValid;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色数据权限
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-06-26
 */
@TableName("iam_role_data_power")
@Schema(name = "IamRoleDataPower对象", description = "角色数据权限")
public class IamRoleDataPower implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(name = "主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(groups = {PutValid.class}, message = "id is null")
    private Long id;

    @Schema(name = "角色id")
    @NotNull(groups = {PostValid.class}, message = "iamRoleId is null")
    private Long iamRoleId;

    @Schema(name = "部门id")
    @NotNull(groups = {PostValid.class}, message = "iamDeptId is null")
    private Long iamDeptId;

    @Schema(name = "租户id")
    @NotNull(groups = {PostValid.class}, message = "iamTenantId is null")
    private Long iamTenantId;

    private LocalDateTime createTime;

    private Long createBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIamRoleId() {
        return iamRoleId;
    }

    public void setIamRoleId(Long iamRoleId) {
        this.iamRoleId = iamRoleId;
    }

    public Long getIamDeptId() {
        return iamDeptId;
    }

    public void setIamDeptId(Long iamDeptId) {
        this.iamDeptId = iamDeptId;
    }

    public Long getIamTenantId() {
        return iamTenantId;
    }

    public void setIamTenantId(Long iamTenantId) {
        this.iamTenantId = iamTenantId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    @Override
    public String toString() {
        return "IamRoleDataPower{" +
            "id=" + id +
            ", iamRoleId=" + iamRoleId +
            ", iamDeptId=" + iamDeptId +
            ", iamTenantId=" + iamTenantId +
            ", createTime=" + createTime +
            ", createBy=" + createBy +
            '}';
    }
}
