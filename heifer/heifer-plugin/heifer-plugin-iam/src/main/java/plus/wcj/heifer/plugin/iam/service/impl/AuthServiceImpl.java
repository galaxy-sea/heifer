package plus.wcj.heifer.plugin.iam.service.impl;

import com.nimbusds.jwt.JWTClaimsSet;
import plus.wcj.heifer.metadata.exception.ResultException;
import plus.wcj.heifer.metadata.exception.ResultStatusEnum;
import plus.wcj.heifer.metadata.properties.JwtProperties;
import plus.wcj.heifer.metadata.tenant.DataPowersDto;
import plus.wcj.heifer.metadata.tenant.UserPrincipalService;
import plus.wcj.heifer.plugin.iam.dao.AuthDao;
import plus.wcj.heifer.plugin.iam.dto.AccountDto;
import plus.wcj.heifer.plugin.iam.dto.JwtDto;
import plus.wcj.heifer.plugin.iam.dto.LoginDto;
import plus.wcj.heifer.plugin.iam.dto.RoleDto;
import plus.wcj.heifer.plugin.iam.dto.TenantDto;
import plus.wcj.heifer.plugin.iam.service.AuthService;
import plus.wcj.heifer.tools.utils.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/12/23
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthServiceImpl implements AuthService, UserPrincipalService {

    private final AuthDao authDao;
    private final PasswordEncoder passwordEncoder;

    private final JwtProperties jwtProperties;

    @Override
    public JwtDto login(LoginDto loginDto) {
        AccountDto account = authDao.selectAccountByPhone(loginDto.getPhone());
        if (account == null || !passwordEncoder.matches(loginDto.getPassword(), account.getPassword())) {
            throw new ResultException(ResultStatusEnum.INCORRECT_USERNAME_OR_PASSWORD);
        }
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                // iss – 发行人声明
                .issuer("iss")
                //sub – 主题声明
                .subject(account.getUsername())
                // aud – 受众声明
                .audience("aud")
                // exp – 过期时间
                .expirationTime(new Date(System.currentTimeMillis() + (loginDto.getRememberMe() != null && loginDto.getRememberMe() ? this.jwtProperties.getRemember() : this.jwtProperties.getTtl())))
                // nbf – 声明不可用
                .notBeforeTime(new Date())
                // iat – 发出的声明
                .issueTime(new Date())
                // jti – JWT ID 声明
                .jwtID(account.getId().toString()).build();
        String jwt = JwtUtil.createJwt(claimsSet, jwtProperties.getKey());
        return new JwtDto(jwt);
    }

    public List<TenantDto> listAllTenant(String authorization) {
        JWTClaimsSet jwtClaimsSet = JwtUtil.parseAuthorization(authorization, jwtProperties.getKey());
        String accountId = jwtClaimsSet.getJWTID();
        return authDao.selectAllTenant(accountId);
    }

    @Override
    public List<String> listPermission(Long accountId, Long tenantId) {
        List<RoleDto> roleList = authDao.listRole(accountId, tenantId);
        List<String> permissionList = authDao.listPermission(accountId, tenantId, roleList);

        return Stream.concat(roleList.stream().map(role -> "ROLE_" + role.getName()),
                             permissionList.stream()
        ).collect(Collectors.toList());
    }

    @Override
    public DataPowersDto listPower(Long accountId, Long tenantId) {
        List<Long> dataPowers = authDao.listPower(accountId, tenantId);
        Long deptId = authDao.getDept(accountId, tenantId);

        DataPowersDto dataPowersDto = new DataPowersDto();
        dataPowersDto.setDeptId(deptId);
        dataPowersDto.setDataPowers(dataPowers);
        // TODO: 2022/3/13 changjin wei(魏昌进) 保留字段，目前不做扩展
        dataPowersDto.setTenantDataPower(Boolean.FALSE);
        dataPowersDto.setDeptDataPower(Boolean.FALSE);
        return dataPowersDto;
    }
}
