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

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-06-26
 */
@TableName("iam_role")
@ApiModel(value = "IamRole对象", description = "角色表")
public class IamRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(groups = {PutValid.class}, message = "id is null")
    private Long id;

    @ApiModelProperty("租户id")
    @NotNull(groups = {PostValid.class}, message = "iamTenantId is null")
    private Long iamTenantId;

    @ApiModelProperty("部门id")
    @NotNull(groups = {PostValid.class}, message = "iamDeptId is null")
    private Long iamDeptId;

    @ApiModelProperty("名称")
    @NotNull(groups = {PostValid.class}, message = "name is null")
    private String name;

    private LocalDateTime createTime;

    private Long createBy;

    private LocalDateTime updateTime;

    private Long updateBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    @Override
    public String toString() {
        return "IamRole{" +
            "id=" + id +
            ", iamTenantId=" + iamTenantId +
            ", iamDeptId=" + iamDeptId +
            ", name='" + name + '\'' +
            ", createTime=" + createTime +
            ", createBy=" + createBy +
            ", updateTime=" + updateTime +
            ", updateBy=" + updateBy +
            '}';
    }
}
