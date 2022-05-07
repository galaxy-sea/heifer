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

package plus.wcj.heifer.plugin.iam.service;


import plus.wcj.heifer.plugin.iam.dto.JwtDto;
import plus.wcj.heifer.plugin.iam.dto.LoginDto;
import plus.wcj.heifer.plugin.iam.dto.TenantDto;

import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/12/23
 */
public interface AuthService {
    /**
     * 登陆，
     * @param loginDto 登陆信息
     * @return jwt
     */
    JwtDto login(LoginDto loginDto);

    /**
     * 获取当前账户下的租户信息
     * @param authorization jwt
     * @return 租户信息列表
     */
    List<TenantDto> listAllTenant(String authorization);
}
