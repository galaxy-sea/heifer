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

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 登录请求参数
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2022-01-13
 */
public class LoginDto {

    /**
     * 用户名或邮箱或手机号
     */
    @NotEmpty
    private String phone;

    /**
     * 密码
     */
    @NotEmpty
    private String password;

    /**
     * 记住我
     */
    private Boolean rememberMe;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
            "phone='" + phone + '\'' +
            ", password='" + password + '\'' +
            ", rememberMe=" + rememberMe +
            '}';
    }
}
