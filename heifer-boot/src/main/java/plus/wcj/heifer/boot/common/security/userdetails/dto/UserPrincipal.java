package plus.wcj.heifer.boot.common.security.userdetails.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 自定义User
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-10 15:09
 */
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class UserPrincipal implements UserDetails {
    /** 主键 */
    private final Long id;

    /** 用户名 */
    private final String username;

    /** 密码 */
    @JsonIgnore
    private final String password;

    /** 状态，启用-1，禁用-0 */
    private final Boolean isEnabled;

    /** 组织id */
    private final Long orgId;

    /** 部门id */
    private final Long deptId;

    /** 全部数据权限，T全部，F部分 */
    private final Boolean allPower;

    /** 全部功能权限，T全部，F部分 */
    private final Boolean allAuthority;

    /** 用户角色列表 */
    private final String roles;

    /** 功能权限 */
    private final String permissions;
    /** 数据权限 */
    private final String dataPowers;

    private Set<? extends GrantedAuthority> authorities;

    public static UserPrincipal create(@NotNull RbacUserDto user, List<RbacRoleDto> roleList, RbacAdminDto admin, RbacCustomerDto customer, RbacUserManageDto userManage, List<RbacPermissionDto> permissionList, List<Long> dataPowerList) {

        String roles = roleList.stream()
                               .map(RbacRoleDto::getName)
                               .filter(StringUtils::isNotBlank)
                               .collect(Collectors.joining(","));

        String permissions = permissionList.stream()
                                           .map(RbacPermissionDto::getPermission)
                                           .filter(StringUtils::isNotBlank)
                                           .collect(Collectors.joining(","));
        String dataPowers = dataPowerList.stream()
                                         .filter(ObjectUtils::isNotEmpty)
                                         .map(Objects::toString)
                                         .collect(Collectors.joining(","));

        return new UserPrincipal(user.getId(), user.getUsername(), user.getPassword(),
                                 user.getIsEnabled(), user.getRbacOrgId(), admin.getRbacDeptId(),
                                 userManage.getAllPower(), userManage.getAllAuthority(),
                                 roles, permissions, dataPowers, null
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.authorities == null) {
            this.authorities = Stream.concat(Arrays.stream(this.roles.split(",")).map(s -> "ROLE_" + s), Arrays.stream(this.permissions.split(",")))
                                     .map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
        }
        return this.authorities;
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
