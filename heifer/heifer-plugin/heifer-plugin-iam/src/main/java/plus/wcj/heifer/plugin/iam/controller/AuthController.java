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

package plus.wcj.heifer.plugin.iam.controller;


import plus.wcj.heifer.metadata.annotation.IgnoreWebSecurity;
import plus.wcj.heifer.metadata.annotation.ResponseBodyResult;
import plus.wcj.heifer.plugin.iam.dto.JwtDto;
import plus.wcj.heifer.plugin.iam.dto.LoginDto;
import plus.wcj.heifer.plugin.iam.dto.TenantDto;
import plus.wcj.heifer.plugin.iam.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * 认证 Controller，包括用户注册，用户登录请求
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2022-01-13
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@ResponseBodyResult
public class AuthController {

    private final AuthService authService;

    /**
     * 登录
     */
    @PostMapping("/login")
    @IgnoreWebSecurity
    public JwtDto login(@Validated @RequestBody LoginDto loginDto) {
        return this.authService.login(loginDto);
    }

    /**
     * 登录
     *
     * @return  返回租户列表信息
     */
    @GetMapping("/tenant")
    public List<TenantDto> tenant(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {

        // TODO: 2022/4/29 changjin wei(魏昌进) 这里有个问题，要不要Spring Security进行拦截呐？
        return this.authService.listAllTenant(authorization);
    }

}
