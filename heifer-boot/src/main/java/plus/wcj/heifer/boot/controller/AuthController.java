package plus.wcj.heifer.boot.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.common.security.JwtUtil;
import plus.wcj.heifer.boot.common.security.dto.JwtResponse;
import plus.wcj.heifer.boot.common.security.dto.LoginRequest;
import plus.wcj.heifer.boot.entity.rbac.RbacUserDo;
import plus.wcj.heifer.boot.service.rbac.RbacUserService;

import javax.servlet.http.HttpServletRequest;


/**
 * <p>
 * 认证 Controller，包括用户注册，用户登录请求
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-07 17:23
 */
@Slf4j
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RbacUserService rbacUserService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * 登录
     *
     * @return
     */
    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmailOrPhone(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtil.createJWT(authentication, loginRequest.getRememberMe());
        return new JwtResponse(jwt);
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        try {
            // 设置JWT过期
            jwtUtil.invalidateJWT(request);
        } catch (SecurityException e) {
            return "Status.UNAUTHORIZED";
        }
        return "Status.LOGOUT";
    }

    @GetMapping
    private RbacUserDo createUser() {

        RbacUserDo rbacUserDo = new RbacUserDo();
        rbacUserDo.setTenantOrgId(1L);
        rbacUserDo.setRbacDeptId(1L);
        rbacUserDo.setUsername("xiaowei");
        rbacUserDo.setPhone("xaowei");
        rbacUserDo.setEmail("xiaowei@qq.com");
        rbacUserDo.setPassword(bCryptPasswordEncoder.encode("xiaowei"));
        rbacUserDo.setNickname("xiaowei");
        rbacUserDo.setIsAccountNonExpired(false);
        rbacUserDo.setIsAccountNonLocked(false);
        rbacUserDo.setIsCredentialsNonExpired(false);
        rbacUserDo.setIsEnabled(false);

        rbacUserService.save(rbacUserDo);
        return rbacUserDo;
    }
}
