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

package plus.wcj.heifer.plugin.iam.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 账户表
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AccountDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 账户名 */

    private String username;

    /** 手机号 */

    private String phone;

    /** 邮箱 */

    private String email;

    /** 密码 */

    private String password;

    /** 昵称 */

    private String nickname;

    /** 指示账户的账户是否已过期。过期的账户无法通过身份验证。返回值：true如果账户的账户是否有效（即未过期），false如果不再有效（即到期） */

    private boolean isAccountNonExpired;

    /** 指示账户是锁定还是解锁。锁定的账户无法通过身份验证。返回值：true如果账户没有被锁定，false否则 */

    private boolean isAccountNonLocked;

    /** 指示账户的凭据（密码）是否已过期。过期的凭据会阻止身份验证。返回值：true如果账户的证书是有效的（即非到期），false如果不再有效（即到期 */

    private boolean isCredentialsNonExpired;

    /** 指示账户是启用还是禁用。禁用的账户无法通过身份验证。返回值：true如果账户已启用，false否则 */

    private boolean isEnabled;
}
