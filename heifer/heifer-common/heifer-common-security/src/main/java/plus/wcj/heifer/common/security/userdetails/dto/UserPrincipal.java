package plus.wcj.heifer.common.security.userdetails.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.BooleanUtils;

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
 * @author yangkai.shen
 * @date Created in 2018-12-10 15:09
 */
public class UserPrincipal implements UserDetails {
    /** 主键 */
    private Long id;

    /** 用户名 */
    private String username;

    /** 密码 */
    @JsonIgnore
    private String password;

    /** 状态，启用-1，禁用-0 */
    private Boolean isEnabled;

    /** 租户id */
    private Long tenantId;

    /** 部门id */
    private Long deptId;

    /** 功能权限 */
    private List<String> permissions;
    /** 数据权限 */
    private String dataPowers;

    private List<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(RbacAccountDto account, RbacAccountManageDto accountManage) {
        UserPrincipal userPrincipal = new UserPrincipal();
        userPrincipal.id = account.getId();
        userPrincipal.username = account.getUsername();
        userPrincipal.isEnabled = account.isEnabled();
        userPrincipal.tenantId = accountManage.getRbacTenantId();
        userPrincipal.deptId = accountManage.getRbacDeptId();
        return userPrincipal;
    }

    public static UserPrincipal create(RbacAccountDto account, List<String> permissions) {
        UserPrincipal userPrincipal = new UserPrincipal();
        userPrincipal.id = account.getId();
        userPrincipal.username = account.getUsername();
        userPrincipal.isEnabled = account.isEnabled();
        userPrincipal.tenantId = account.getAccountManage().getRbacTenantId();
        userPrincipal.deptId = account.getAccountManage().getRbacDeptId();
        userPrincipal.permissions = permissions;
        return userPrincipal;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.authorities == null) {
            this.authorities = this.permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
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
        return BooleanUtils.isTrue(this.isEnabled);
    }

    public Long getId() {
        return id;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public String getDataPowers() {
        return dataPowers;
    }
}
