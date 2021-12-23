package plus.wcj.heifer.plugin.rbac.controller;


import plus.wcj.heifer.matedata.annotation.ResultResponseBody;
import plus.wcj.heifer.matedata.tenant.Tenant;
import plus.wcj.heifer.plugin.rbac.pojo.dto.LoginDto;
import plus.wcj.heifer.plugin.rbac.pojo.vo.JwtVo;
import plus.wcj.heifer.plugin.rbac.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
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

    private final AuthService authService;

    /**
     * 登录
     */
    @PostMapping("/login")
    public JwtVo login(@Validated @RequestBody LoginDto loginDto) {
        RbacAccountDto accountDto = this.authService.login(loginDto.getPhone(), loginDto.getPassword());
        String jwt = Jwtutil.createJwt(accountDto, true);
        return new JwtResponse(jwt);
    }


    /**
     * 登录
     *
     * @return
     */
    @GetMapping("/tenant")
    @PreAuthorize("isAuthenticated()")
    public List<RbacTenantDto> tenant(Tenant tenant) {
        List<RbacTenantDto> allTenant = authService.getAllTenant(tenant.getAccountId());
        return allTenant;
    }


    @PostMapping("/logout")
    @SuppressWarnings("all")
    public String logout(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        return "Status.LOGOUT";
    }
}
