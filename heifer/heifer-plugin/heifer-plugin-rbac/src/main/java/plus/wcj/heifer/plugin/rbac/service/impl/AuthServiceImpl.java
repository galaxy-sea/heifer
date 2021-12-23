package plus.wcj.heifer.plugin.rbac.service.impl;

import com.nimbusds.jwt.JWTClaimsSet;
import org.apache.commons.lang3.BooleanUtils;
import plus.wcj.heifer.matedata.exception.ResultException;
import plus.wcj.heifer.matedata.exception.ResultStatusEnum;
import plus.wcj.heifer.matedata.properties.JwtProperties;
import plus.wcj.heifer.plugin.rbac.pojo.dto.JwtDto;
import plus.wcj.heifer.plugin.rbac.pojo.dto.LoginDto;
import plus.wcj.heifer.plugin.rbac.pojo.dto.TenantDto;
import plus.wcj.heifer.plugin.rbac.pojo.entity.account.RbacAccount;
import plus.wcj.heifer.plugin.rbac.service.AuthService;
import plus.wcj.heifer.plugin.rbac.service.account.RbacAccountService;
import plus.wcj.heifer.tools.crypto.password.PasswordConfig;
import plus.wcj.heifer.tools.utils.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/12/23
 */
@Service
@Import(value = {PasswordConfig.class, JwtProperties.class})
@RequiredArgsConstructor(onConstructor = @__(@Autowired))

public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final RbacAccountService rbacAccountService;
    private final JwtProperties jwtProperties;

    @Override
    public JwtDto login(LoginDto loginDto) {
        RbacAccount rbacAccount = rbacAccountService.get(new RbacAccount().setPhone(loginDto.getPhone()));
        if (rbacAccount == null || !passwordEncoder.matches(loginDto.getPassword(), rbacAccount.getPassword())) {
            throw new ResultException(ResultStatusEnum.INCORRECT_USERNAME_OR_PASSWORD);
        }
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                // iss – 发行人声明
                .issuer("iss")
                //sub – 主题声明
                .subject(rbacAccount.getUsername())
                // aud – 受众声明
                .audience("aud")
                // exp – 过期时间
                .expirationTime(new Date(System.currentTimeMillis() + (BooleanUtils.isTrue(loginDto.getRememberMe()) ? this.jwtProperties.getRemember() : this.jwtProperties.getTtl())))
                // nbf – 声明不可用
                .notBeforeTime(new Date())
                // iat – 发出的声明
                .issueTime(new Date())
                // jti – JWT ID 声明
                .jwtID(rbacAccount.getId().toString()).build();
        String jwt = JwtUtil.createJwt(claimsSet, jwtProperties.getKey());
        return new JwtDto(jwt);
    }

    @Override
    public List<TenantDto> getAllTenant(String authorization) {
        JWTClaimsSet jwtClaimsSet = JwtUtil.parseAuthorization(authorization, jwtProperties.getKey());
        String accountId = jwtClaimsSet.getJWTID();
        return rbacAccountService.getAllTenant(accountId);
    }
}
