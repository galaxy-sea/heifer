package plus.wcj.heifer.common.security;


import lombok.Data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
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
@Data
public class UserPrincipal implements UserDetails {
    /** 主键 */
    private Long id;

    /** 用户名 */
    private String username;

    /** 密码 */
    private String password;

    /** 状态，启用-1，禁用-0 */
    private boolean isEnabled;

    /** 租户id */
    private Long tenantId;

    /** 部门id */
    private Long deptId;

    /** 功能权限 */
    private List<String> permissions;
    /** 数据权限 */
    private String dataPowers;

    private List<? extends GrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.authorities == null) {
            if (this.permissions == null) {
                this.authorities = new ArrayList<>();
            } else {
                this.authorities = this.permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
            }
            this.permissions = null;
        }
        return this.authorities;
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
}
