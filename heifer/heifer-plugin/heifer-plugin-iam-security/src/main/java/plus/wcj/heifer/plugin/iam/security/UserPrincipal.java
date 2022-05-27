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

package plus.wcj.heifer.plugin.iam.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 自定义User
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2022-01-13
 */
public class UserPrincipal implements UserDetails {
    /** 主键 */
    private final Long id;

    /** 用户名 */
    private final String username;

    /** 密码 */
    private String password;

    /** 状态，启用-1，禁用-0 */
    private final boolean isEnabled;

    /** 租户id */
    private final Long tenantId;
    private List<? extends GrantedAuthority> authorities;

    public UserPrincipal(Long id, String username, Long tenantId, boolean isEnabled) {
        this.id = id;
        this.username = username;
        this.isEnabled = isEnabled;
        this.tenantId = tenantId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setPermissions(List<String> permissions) {
        this.authorities = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    public Long getId() {
        return id;
    }

    public Long getTenantId() {
        return tenantId;
    }
}
