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
 * 开放平台信息
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-06-26
 */
@TableName("iam_access")
@ApiModel(value = "IamAccess对象", description = "开放平台信息")
public class IamAccess implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(groups = {PutValid.class}, message = "id is null")
    private Long id;

    @ApiModelProperty("账户id")
    @NotNull(groups = {PostValid.class}, message = "iamAccountManageId is null")
    private Long iamAccountManageId;

    @ApiModelProperty("access_key_id")
    @NotNull(groups = {PostValid.class}, message = "accessKeyId is null")
    private String accessKeyId;

    @ApiModelProperty("access_key_secret")
    @NotNull(groups = {PostValid.class}, message = "accessKeySecret is null")
    private String accessKeySecret;

    private LocalDateTime createTime;

    private Long createBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIamAccountManageId() {
        return iamAccountManageId;
    }

    public void setIamAccountManageId(Long iamAccountManageId) {
        this.iamAccountManageId = iamAccountManageId;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
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
        return "IamAccess{" +
            "id=" + id +
            ", iamAccountManageId=" + iamAccountManageId +
            ", accessKeyId='" + accessKeyId + '\'' +
            ", accessKeySecret='" + accessKeySecret + '\'' +
            ", createTime=" + createTime +
            ", createBy=" + createBy +
            '}';
    }
}
