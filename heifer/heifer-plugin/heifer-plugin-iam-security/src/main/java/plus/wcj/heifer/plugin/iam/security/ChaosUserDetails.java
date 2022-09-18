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
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 自定义User
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2022-01-13
 */
public class ChaosUserDetails implements UserDetails, UserPrincipal {

    private static final List<? extends GrantedAuthority> AUTHORITIES = Collections.emptyList();

    public ChaosUserDetails() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AUTHORITIES;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public void setPermissions(List<String> permissions) {

    }

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public Long getTenantId() {
        return null;
    }

    @Override
    public Date getExpirationTime() {
        return null;
    }

    @Override
    public Map<String, String> getMetadata() {
        return null;
    }
}
