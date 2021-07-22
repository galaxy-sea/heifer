package plus.wcj.heifer.boot.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.common.mvc.result.Result;
import plus.wcj.heifer.boot.common.security.dto.JwtResponse;
import plus.wcj.heifer.boot.common.security.dto.LoginRequest;
import plus.wcj.heifer.boot.common.security.jwt.JwtUtil;
import plus.wcj.heifer.boot.common.security.userdetails.HeiferUserDetailsServiceImpl;
import plus.wcj.heifer.boot.common.security.userdetails.dto.UserPrincipal;
import plus.wcj.heifer.boot.entity.rbac.user.RbacUser;
import plus.wcj.heifer.boot.service.rbac.user.RbacUserService;

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
    private final HeiferUserDetailsServiceImpl heiferUserDetailsServiceImpl;
    private final JwtUtil jwtUtil;

    private final RbacUserService userService;

    private final HeiferUserDetailsServiceImpl userDetailsService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest loginRequest) {
        RbacUser rbacUser = this.userService.get(new RbacUser().setNickname(loginRequest.getUsernameOrEmailOrPhone()).setRbacOrgId(1L));
        Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmailOrPhone(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = this.jwtUtil.createJwt(authentication, loginRequest.getRememberMe());
        return new JwtResponse(jwt);
    }


    @PostMapping("/sign-up")
    public Result<Void> signUp(@RequestBody LoginRequest loginRequest) {
        this.userService.signUp(loginRequest);
        return Result.success();
    }

    @PostMapping("/logout")
    @SuppressWarnings("all")
    public String logout(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        // 设置JWT过期
        jwtUtil.invalidateJwt(header);

        return "Status.LOGOUT";
    }


    /**
     * 登录
     */
    @PostMapping("/login/test")
    public JwtResponse login() {
        UserPrincipal admin = (UserPrincipal) this.heiferUserDetailsServiceImpl.loadUserByUsername("1");
        String jwt = this.jwtUtil.createJwt(admin, true);
        return new JwtResponse(jwt);
    }

    @GetMapping("/user")
    public UserPrincipal logout(UserPrincipal userPrincipal) {
        return userPrincipal;
    }

}
