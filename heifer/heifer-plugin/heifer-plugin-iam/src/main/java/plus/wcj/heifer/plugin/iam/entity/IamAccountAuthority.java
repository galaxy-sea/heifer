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

import plus.wcj.heifer.metadata.annotation.PostValid;
import plus.wcj.heifer.metadata.annotation.PutValid;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 账户拥有功能权限表
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-06-26
 */
@TableName("iam_account_authority")
@ApiModel(value = "IamAccountAuthority对象", description = "账户拥有功能权限表")
public class IamAccountAuthority implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(groups = {PutValid.class}, message = "id is null")
    private Long id;

    @ApiModelProperty("账户id")
    @NotNull(groups = {PostValid.class}, message = "iamAccountId is null")
    private Long iamAccountId;

    @ApiModelProperty("租户id")
    @NotNull(groups = {PostValid.class}, message = "iamTenantId is null")
    private Long iamTenantId;

    @ApiModelProperty("功能权限id")
    @NotNull(groups = {PostValid.class}, message = "iamPermissionId is null")
    private Long iamPermissionId;

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

    public Long getIamPermissionId() {
        return iamPermissionId;
    }

    public void setIamPermissionId(Long iamPermissionId) {
        this.iamPermissionId = iamPermissionId;
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
        return "IamAccountAuthority{" +
            "id=" + id +
            ", iamAccountId=" + iamAccountId +
            ", iamTenantId=" + iamTenantId +
            ", iamPermissionId=" + iamPermissionId +
            ", createTime=" + createTime +
            ", createBy=" + createBy +
            '}';
    }
}
