package plus.wcj.heifer.boot.common.security.userdetails;


import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import plus.wcj.heifer.boot.common.security.userdetails.dao.CustomUserDetailsDao;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacPermissionDto;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacRoleDto;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacUserDto;
import plus.wcj.heifer.boot.common.security.userdetails.dto.UserPrincipal;
import plus.wcj.heifer.boot.common.tenant.ClientProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 自定义UserDetails查询
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-10 10:29
 */
@Service
@EnableConfigurationProperties(ClientProperties.class)
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomUserDetailsDao customUserDetailsDao;
    @Autowired
    private ClientProperties clientProperties;


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmailOrPhone) throws UsernameNotFoundException {
        // TODO: 2021/5/23 changjin wei(魏昌进) 修改表结构
        RbacUserDto user = customUserDetailsDao.findUserByUsernameOrEmailOrPhone(usernameOrEmailOrPhone, usernameOrEmailOrPhone, usernameOrEmailOrPhone).orElseThrow(() -> new UsernameNotFoundException("未找到用户信息 : " + usernameOrEmailOrPhone));
        List<RbacRoleDto> roles = customUserDetailsDao.selectRoleByUserId(user.getId());
        List<RbacPermissionDto> permissions = listPermission(roles, user.getId());
        return UserPrincipal.create(user, roles, permissions);
    }

    private List<RbacPermissionDto> listPermission(List<RbacRoleDto> roles, Long userId) {
        List<RbacPermissionDto> allPermissions = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(roles)) {
            List<Long> roleIds = roles.stream().map(RbacRoleDto::getId).collect(Collectors.toList());
            List<RbacPermissionDto> rolePermission = customUserDetailsDao.selectPermissionByRoleIdList(roleIds);
            if (CollectionUtils.isNotEmpty(rolePermission)) {
                allPermissions = customUserDetailsDao.selectPermissionByRoleIdList(roleIds);
            }
        }
        List<RbacPermissionDto> userPermission = customUserDetailsDao.selectPermissionByUserId(userId);
        if (CollectionUtils.isNotEmpty(userPermission)) {
            allPermissions.addAll(userPermission);
        }
        return allPermissions;
    }
}
