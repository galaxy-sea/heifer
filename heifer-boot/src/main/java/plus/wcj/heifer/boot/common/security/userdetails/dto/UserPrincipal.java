package plus.wcj.heifer.boot.common.security.userdetails.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 自定义User
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-10 15:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPrincipal implements UserDetails {
    /** 主键 */
    private Long id;

    /** 用户名 */
    private String username;

    /** 密码 */
    @JsonIgnore
    private String password;

    /** 昵称 */
    private String nickname;

    /** 手机 */
    private String phone;

    /** 邮箱 */
    private String email;

    /** 状态，启用-1，禁用-0 */
    private Boolean isEnabled;

    /** 组织id */
    private Long orgId;

    /** 部门id */
    private Long deptId;

    /** 用户角色列表 */
    private Set<String> roles;

    /** 功能权限 */
    private Set<? extends GrantedAuthority> authorities;
    /** 数据权限 */
    private Set<Long> dataPowers;

    private RbacAdminDto admin;
    private RbacCustomerDto customer;
    private RbacUserManageDto userManage;

    public static UserPrincipal create(@NotNull RbacUserDto user, List<RbacRoleDto> roles, RbacAdminDto admin, RbacCustomerDto customer, RbacUserManageDto userManage, List<RbacPermissionDto> permissions, List<Long> dataPowers) {

        Set<String> roleNames = roles.stream().map(RbacRoleDto::getName).collect(Collectors.toSet());

        Set<GrantedAuthority> authorities = permissions.stream()
                                                       .filter(permission -> StringUtils.isNotBlank(permission.getPermission()))
                                                       .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                                                       .collect(Collectors.toSet());

        Set<Long> setDataPower = new HashSet<>(dataPowers);

        return new UserPrincipal(user.getId(), user.getUsername(), user.getPassword(),
                                 user.getNickname(), user.getPhone(), user.getEmail(),
                                 user.getIsEnabled(),
                                 null, null, roleNames,
                                 authorities, setDataPower,
                                 admin, customer, userManage
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return BooleanUtils.isTrue(isEnabled);
    }
}
