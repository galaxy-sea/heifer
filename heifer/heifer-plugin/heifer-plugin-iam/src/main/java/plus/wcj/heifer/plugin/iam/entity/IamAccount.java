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
 * 账户表
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-06-26
 */
@Data
@TableName("iam_account")
@ApiModel(value = "IamAccount对象", description = "账户表")
public class IamAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    @NotNull(groups = {PutValid.class}, message = "id is null")
    private Long id;

    @ApiModelProperty("账户名")
    @NotNull(groups = {PostValid.class}, message = "username is null")
    private String username;

    @ApiModelProperty("手机号")
    @NotNull(groups = {PostValid.class}, message = "phone is null")
    private String phone;

    @ApiModelProperty("邮箱")
    @NotNull(groups = {PostValid.class}, message = "email is null")
    private String email;

    @ApiModelProperty("密码")
    @NotNull(groups = {PostValid.class}, message = "password is null")
    private String password;

    @ApiModelProperty("昵称")
    @NotNull(groups = {PostValid.class}, message = "nickname is null")
    private String nickname;

    @ApiModelProperty("指示账户是启用还是禁用。禁用的账户无法通过身份验证。返回值：true如果账户已启用，false否则")
    @NotNull(groups = {PostValid.class}, message = "isEnabled is null")
    private Boolean isEnabled;

    private LocalDateTime createTime;

    private Long createBy;

    private LocalDateTime updateTime;

    private Long updateBy;


}
