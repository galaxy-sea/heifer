package plus.wcj.heifer.boot.common.security.userdetails;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import plus.wcj.heifer.boot.common.security.userdetails.dto.Permission;
import plus.wcj.heifer.boot.common.security.userdetails.dto.Role;
import plus.wcj.heifer.boot.common.security.userdetails.dto.User;
import plus.wcj.heifer.boot.common.security.userdetails.dto.UserPrincipal;
import plus.wcj.heifer.boot.common.security.userdetails.dao.CustomUserDetailsDao;

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
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomUserDetailsDao customUserDetailsDao;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmailOrPhone) throws UsernameNotFoundException {
        // TODO: 2021/5/23 changjin wei(魏昌进) 修改表结构
        User user = customUserDetailsDao.findUserByUsernameOrEmailOrPhone(usernameOrEmailOrPhone, usernameOrEmailOrPhone, usernameOrEmailOrPhone).orElseThrow(() -> new UsernameNotFoundException("未找到用户信息 : " + usernameOrEmailOrPhone));
        List<Role> roles = customUserDetailsDao.selectRoleByUserId(user.getId());
        List<Long> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
        List<Permission> permissions = customUserDetailsDao.selectPermissionByRoleIdList(roleIds);
        return UserPrincipal.create(user, roles, permissions);
    }
}
