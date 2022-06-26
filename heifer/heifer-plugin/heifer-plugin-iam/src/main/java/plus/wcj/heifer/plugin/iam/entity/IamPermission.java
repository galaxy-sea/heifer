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
 * 功能权限
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-06-26
 */
@Data
@TableName("iam_permission")
@ApiModel(value = "IamPermission对象", description = "功能权限")
public class IamPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(groups = {PutValid.class}, message = "id is null")
    private Long id;

    @ApiModelProperty("父节点名称")
    @NotNull(groups = {PostValid.class}, message = "parentId is null")
    private Long parentId;

    @ApiModelProperty("权限名称")
    @NotNull(groups = {PostValid.class}, message = "name is null")
    private String name;

    @ApiModelProperty("权限表达式，用:分割")
    @NotNull(groups = {PostValid.class}, message = "permission is null")
    private String permission;

    @ApiModelProperty("client:客户端,menu:菜单,button:按钮")
    @NotNull(groups = {PostValid.class}, message = "type is null")
    private String type;

    @ApiModelProperty("排序，默认asc")
    @NotNull(groups = {PostValid.class}, message = "sort is null")
    private Integer sort;

    private LocalDateTime createTime;

    private Long createBy;

    private LocalDateTime updateTime;

    private Long updateBy;


}
