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
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.NotNull;
import plus.wcj.heifer.metadata.annotation.PutValid;
import plus.wcj.heifer.metadata.annotation.PostValid;

/**
 * <p>
 * abac策略规则
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-06-26
 */
@Data
@TableName("iam_strategy")
@ApiModel(value = "IamStrategy对象", description = "abac策略规则")
public class IamStrategy implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(groups = {PutValid.class}, message = "id is null")
    private Long id;

    @ApiModelProperty("策略，需要硬编码")
    @NotNull(groups = {PostValid.class}, message = "strategyEnum is null")
    private String strategyEnum;

    @ApiModelProperty("策略名称")
    @NotNull(groups = {PostValid.class}, message = "strategyName is null")
    private String strategyName;

    private Long createBy;

    private LocalDateTime createTime;


}
