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
 * 账户与租户的绑定关系
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-06-26
 */
@TableName("iam_account_manage")
@Schema(name = "IamAccountManage对象", description = "账户与租户的绑定关系")
public class IamAccountManage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(name = "主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(groups = {PutValid.class}, message = "id is null")
    private Long id;

    @Schema(name = "账户id")
    @NotNull(groups = {PostValid.class}, message = "iamAccountId is null")
    private Long iamAccountId;

    @Schema(name = "租户id")
    @NotNull(groups = {PostValid.class}, message = "iamTenantId is null")
    private Long iamTenantId;

    @Schema(name = "部门id")
    @NotNull(groups = {PostValid.class}, message = "iamDeptId is null")
    private Long iamDeptId;

    @Schema(name = "是否超级管理员")
    @NotNull(groups = {PostValid.class}, message = "superAdmin is null")
    private Boolean superAdmin;

    private LocalDateTime createTime;

    private Long createBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIamAccountId() {
        return iamAccountId;
    }

    public void setIamAccountId(Long iamAccountId) {
        this.iamAccountId = iamAccountId;
    }

    public Long getIamTenantId() {
        return iamTenantId;
    }

    public void setIamTenantId(Long iamTenantId) {
        this.iamTenantId = iamTenantId;
    }

    public Long getIamDeptId() {
        return iamDeptId;
    }

    public void setIamDeptId(Long iamDeptId) {
        this.iamDeptId = iamDeptId;
    }

    public Boolean getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(Boolean superAdmin) {
        this.superAdmin = superAdmin;
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
        return "IamAccountManage{" +
            "id=" + id +
            ", iamAccountId=" + iamAccountId +
            ", iamTenantId=" + iamTenantId +
            ", iamDeptId=" + iamDeptId +
            ", superAdmin=" + superAdmin +
            ", createTime=" + createTime +
            ", createBy=" + createBy +
            '}';
    }
}
