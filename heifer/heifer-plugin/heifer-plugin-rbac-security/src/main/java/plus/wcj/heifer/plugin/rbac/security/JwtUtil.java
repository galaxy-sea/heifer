package plus.wcj.heifer.plugin.rbac.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.util.DateUtils;
import plus.wcj.heifer.common.security.UserPrincipal;
import plus.wcj.heifer.common.security.properties.JwtProperties;
import plus.wcj.heifer.matedata.exception.ResultException;
import plus.wcj.heifer.matedata.exception.ResultStatusEnum;
import plus.wcj.heifer.plugin.rbac.service.account.RbacAccountService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.security.Key;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/12/22
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JwtUtil {

    private final JwtProperties jwtProperties;
    private final RbacAccountService rbacAccountService;
    public static final String BEARER = "Bearer ";

    public static final Long MAX_CLOCK_SKEW_SECONDS = 60L;

    private final JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256)
            // 设置类型 ( typ ) 参数
            .type(JOSEObjectType.JWT)
            // 设置密钥 ID ( kid ) 参数。
            // .keyID("kid")
            .build();

    /**
     * 反序列化
     * @param authorization
     * @param tenantId
     * @return
     */
    public UserPrincipal parseAuthorization(String authorization, Long tenantId) {
        String jwt = authorization.startsWith(BEARER) ? authorization.substring("Bearer ".length()) : authorization;
        JWTClaimsSet jwtClaimsSet = this.parseJwt(jwt, jwtProperties.getKey());
        UserPrincipal userPrincipal = new UserPrincipal();
        Long id = Long.valueOf(jwtClaimsSet.getJWTID());
        userPrincipal.setId(id);
        userPrincipal.setUsername(jwtClaimsSet.getSubject());
        // TODO: 2021/12/22 changjin wei(魏昌进) 权限获取未实现
        List<String> allPermission = rbacAccountService.getAllPermission(id, tenantId);
        List<SimpleGrantedAuthority> authorities = allPermission.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        userPrincipal.setAuthorities(authorities);
        return userPrincipal;
    }


    /**
     * 反序列化
     * @param jwt
     * @return
     */
    private JWTClaimsSet parseJwt(String jwt,String key) {
        try {
            SignedJWT signedJwt = SignedJWT.parse(jwt);
            MACVerifier verifier = new MACVerifier(key);

            this.verify(signedJwt, verifier);
            return signedJwt.getJWTClaimsSet();
        } catch (JOSEException | ParseException e) {
            throw new ResultException(ResultStatusEnum.UNAUTHORIZED);
        }
    }

    /**
     * 校验有效性和时效性
     *
     * @param signedJwt
     * @param verifier
     *
     * @throws ParseException
     * @throws JOSEException
     */
    private void verify(SignedJWT signedJwt, MACVerifier verifier) throws ParseException, JOSEException {

        if (!signedJwt.verify(verifier)) {
            throw new ResultException(ResultStatusEnum.UNAUTHORIZED);
        }
        JWTClaimsSet claimsSet = signedJwt.getJWTClaimsSet();

        // throw new ResultException(ResultStatus.UNAUTHORIZED);
        final Date now = new Date();
        final Date exp = claimsSet.getExpirationTime();

        if (exp == null || !DateUtils.isAfter(exp, now, MAX_CLOCK_SKEW_SECONDS)) {
            throw new ResultException(ResultStatusEnum.EXPIRED_TOKEN);
        }

        final Date nbf = claimsSet.getNotBeforeTime();
        if (nbf == null || !DateUtils.isBefore(nbf, now, MAX_CLOCK_SKEW_SECONDS)) {
            throw new ResultException(ResultStatusEnum.TOKEN_BEFORE_USE_TIME);
        }
    }


}
