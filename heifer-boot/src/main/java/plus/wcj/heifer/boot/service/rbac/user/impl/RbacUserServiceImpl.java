package plus.wcj.heifer.boot.service.rbac.user.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import plus.wcj.heifer.boot.common.security.dto.LoginRequest;
import plus.wcj.heifer.boot.dao.rbac.user.RbacUserDao;
import plus.wcj.heifer.boot.entity.rbac.user.RbacUser;
import plus.wcj.heifer.boot.extension.service.impl.ServiceImpl;
import plus.wcj.heifer.boot.service.rbac.user.RbacUserService;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@Service
@RequiredArgsConstructor
public class RbacUserServiceImpl extends ServiceImpl<RbacUserDao, RbacUser> implements RbacUserService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(LoginRequest loginRequest) {

        RbacUser rbacUser = new RbacUser();
        rbacUser.setRbacOrgId(1L);
        rbacUser.setUsername(loginRequest.getUsernameOrEmailOrPhone());
        rbacUser.setPhone("setPhone");
        rbacUser.setEmail("setEmail");
        rbacUser.setPassword(passwordEncoder.encode(loginRequest.getPassword()));
        rbacUser.setNickname("setNickname");
        rbacUser.setAccountNonExpired(Boolean.TRUE);
        rbacUser.setAccountNonLocked(Boolean.TRUE);
        rbacUser.setCredentialsNonExpired(Boolean.TRUE);
        rbacUser.setEnabled(Boolean.TRUE);
        rbacUser.setCreateBy(1L);
        super.save(rbacUser);
    }
}
