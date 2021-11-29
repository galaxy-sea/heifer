package plus.wcj.heifer.boot.controller;


import plus.wcj.heifer.boot.common.mvc.result.Result;
import plus.wcj.heifer.boot.common.mvc.result.ResultResponseBody;
import plus.wcj.heifer.boot.common.security.dto.JwtResponse;
import plus.wcj.heifer.boot.common.security.dto.LoginRequest;
import plus.wcj.heifer.boot.common.security.jwt.JwtUtil;
import plus.wcj.heifer.boot.common.security.userdetails.HeiferUserDetailsServiceImpl;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacAccountDto;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacTenantDto;
import plus.wcj.heifer.boot.common.security.userdetails.dto.UserPrincipal;
import plus.wcj.heifer.boot.extension.tenant.Tenant;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


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
@RequestMapping("/auth")
@RequiredArgsConstructor
@ResultResponseBody
public class AuthController {

    private final HeiferUserDetailsServiceImpl heiferUserDetailsServiceImpl;
    private final JwtUtil jwtUtil;

    /**
     * 登录
     */
    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody LoginRequest loginRequest) {
        RbacAccountDto accountDto = this.heiferUserDetailsServiceImpl.loadUserByUsername(loginRequest.getUsername(), loginRequest.getPassword());
        String jwt = this.jwtUtil.createJwt(accountDto, true);
        return new JwtResponse(jwt);
    }


    /**
     * 登录
     * @return
     */
    @GetMapping("/tenant")
    @PreAuthorize("isAuthenticated()")
    public List<RbacTenantDto> tenant(Tenant tenant) {
        List<RbacTenantDto> allTenant = heiferUserDetailsServiceImpl.getAllTenant(tenant.getAccountId());
        return allTenant;
    }

    @PostMapping("/sign-up")
    public Result<Void> signUp(@RequestBody LoginRequest loginRequest) {
        // this.userService.signUp(loginRequest);
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

    // TODO: 2021/11/24 changjin wei(魏昌进)
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/info")
    @SuppressWarnings("all")
    public DataInfo info(Tenant tenant) {
        List<String> allPermission = heiferUserDetailsServiceImpl.getAllPermission(tenant.getTenantId(), tenant.getAccountId());
        allPermission.add("admin");


        return DataInfo.builder()
                       .roles(allPermission)
                       .introduction("I am a super administrator")
                       .avatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif")
                       .name("Super Admin")
                       .tenant(1)
                       .build();
    }

    @GetMapping("/user")
    public UserPrincipal logout(UserPrincipal userPrincipal) {
        return userPrincipal;
    }


    @Data
    @Builder
    static class DataInfo {
        private String introduction;
        private String avatar;
        private String name;
        private Integer tenant;
        private List<String> roles;
    }
}
