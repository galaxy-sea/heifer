package plus.wcj.heifer.plugin.iam.controller;


import plus.wcj.heifer.metadata.annotation.IgnoreWebSecurity;
import plus.wcj.heifer.metadata.annotation.ResultResponseBody;
import plus.wcj.heifer.plugin.iam.dto.JwtDto;
import plus.wcj.heifer.plugin.iam.dto.LoginDto;
import plus.wcj.heifer.plugin.iam.dto.TenantDto;
import plus.wcj.heifer.plugin.iam.service.AuthService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * <p>
 * 认证 Controller，包括用户注册，用户登录请求
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2022-01-13
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
    @IgnoreWebSecurity
    public JwtDto login(@Validated @RequestBody LoginDto loginDto) {
        return this.authService.login(loginDto);
    }

    /**
     * 登录
     *
     * @return
     */
    @GetMapping("/tenant")

    public List<TenantDto> tenant(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        return this.authService.listAllTenant(authorization);
    }

}
