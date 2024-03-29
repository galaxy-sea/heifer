/*
 * Copyright 2021-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package plus.wcj.heifer.plugin.iam.auth;

import com.nimbusds.jwt.JWTClaimsSet;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import plus.wcj.heifer.metadata.exception.ResultException;
import plus.wcj.heifer.metadata.exception.ResultStatusEnum;
import plus.wcj.heifer.metadata.iam.DataPowersDto;
import plus.wcj.heifer.metadata.iam.UserPrincipalService;
import plus.wcj.heifer.metadata.properties.JwtProperties;
import plus.wcj.heifer.plugin.iam.auth.permission.PermissionCustomizer;
import plus.wcj.heifer.plugin.iam.dao.AuthDao;
import plus.wcj.heifer.plugin.iam.dto.AccountDto;
import plus.wcj.heifer.plugin.iam.dto.JwtDto;
import plus.wcj.heifer.plugin.iam.dto.LoginDto;
import plus.wcj.heifer.plugin.iam.dto.TenantDto;
import plus.wcj.heifer.tools.util.JwtUtils;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/12/23
 */
@Service
@EnableConfigurationProperties(JwtProperties.class)
public class AuthServiceImpl implements AuthService, UserPrincipalService {

    private final AuthDao authDao;
    private final PasswordEncoder passwordEncoder;
    private final JwtProperties jwtProperties;
    private final List<PermissionCustomizer> permissionCustomizerList;

    public AuthServiceImpl(AuthDao authDao, PasswordEncoder passwordEncoder, JwtProperties jwtProperties, List<PermissionCustomizer> permissionCustomizerList) {
        this.authDao = authDao;
        this.passwordEncoder = passwordEncoder;
        this.jwtProperties = jwtProperties;
        this.permissionCustomizerList = permissionCustomizerList;
    }

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
        String jwt = JwtUtils.createJwt(claimsSet, jwtProperties.getKey());
        return new JwtDto(jwt);
    }

    public List<TenantDto> listAllTenant(String authorization) {
        JWTClaimsSet jwtClaimsSet = JwtUtils.parseAuthorization(authorization, jwtProperties.getKey());
        String accountId = jwtClaimsSet.getJWTID();
        return authDao.selectAllTenant(accountId);
    }

    @Override
    public List<String> listPermission(Long accountId, Long tenantId) {
        return permissionCustomizerList.stream()
                                       .map(permissionCustomizer -> permissionCustomizer.customize(accountId, tenantId))
                                       .flatMap(Collection::stream)
                                       .distinct()
                                       .collect(Collectors.toList());
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
