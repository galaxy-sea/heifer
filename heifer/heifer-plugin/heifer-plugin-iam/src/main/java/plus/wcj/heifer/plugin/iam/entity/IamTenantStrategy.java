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
 * 租户配置的策略规则
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-06-26
 */
@TableName("iam_tenant_strategy")
@ApiModel(value = "IamTenantStrategy对象", description = "租户配置的策略规则")
public class IamTenantStrategy implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(groups = {PutValid.class}, message = "id is null")
    private Long id;

    @ApiModelProperty("abac权限id")
    @NotNull(groups = {PostValid.class}, message = "iamStrategyId is null")
    private Long iamStrategyId;

    @ApiModelProperty("组织id")
    @NotNull(groups = {PostValid.class}, message = "iamTenantId is null")
    private Long iamTenantId;

    @ApiModelProperty("策略值，需要硬编码")
    @NotNull(groups = {PostValid.class}, message = "strategyValue is null")
    private String strategyValue;

    private Long createBy;

    private LocalDateTime createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIamStrategyId() {
        return iamStrategyId;
    }

    public void setIamStrategyId(Long iamStrategyId) {
        this.iamStrategyId = iamStrategyId;
    }

    public Long getIamTenantId() {
        return iamTenantId;
    }

    public void setIamTenantId(Long iamTenantId) {
        this.iamTenantId = iamTenantId;
    }

    public String getStrategyValue() {
        return strategyValue;
    }

    public void setStrategyValue(String strategyValue) {
        this.strategyValue = strategyValue;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "IamTenantStrategy{" +
            "id=" + id +
            ", iamStrategyId=" + iamStrategyId +
            ", iamTenantId=" + iamTenantId +
            ", strategyValue='" + strategyValue + '\'' +
            ", createBy=" + createBy +
            ", createTime=" + createTime +
            '}';
    }
}
