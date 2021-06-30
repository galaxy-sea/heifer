package plus.wcj.heifer.boot.common.security.jwt;


import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.util.DateUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import plus.wcj.heifer.boot.common.exception.ResultException;
import plus.wcj.heifer.boot.common.exception.ResultStatus;
import plus.wcj.heifer.boot.common.security.properties.JwtProperties;
import plus.wcj.heifer.boot.common.security.userdetails.dto.UserPrincipal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * <p>
 * JWT 工具类
 * </p>
 *
 * @author changjin wei
 * @date Created in 2021-06-21
 */
@EnableConfigurationProperties(JwtProperties.class)
@Configuration
@Slf4j
@AllArgsConstructor
public class JwtUtil {

    public static final String ROLES = "roles";
    public static final String AUTHORITIES = "authorities";
    public static final String DATA_POWERS = "dataPowers";
    public static final String IS_ENABLED = "isEnabled";
    public static final String DEPT_ID = "deptId";
    public static final String ORG_ID = "orgId";
    public static final String BEARER = "Bearer ";

    public static final Long MAX_CLOCK_SKEW_SECONDS = 60L;
    private final JwtProperties jwtProperties;

    public String createJwt(@NotNull UserPrincipal userPrincipal, @NotNull Boolean rememberMe) {

        // 创建JWS头，设置签名算法和类型
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256)
                // 设置类型 ( typ ) 参数
                .type(JOSEObjectType.JWT)
                // 设置密钥 ID ( kid ) 参数。
                // .keyID("kid")
                .build();

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                // iss – 发行人声明
                .issuer("iss")
                //sub – 主题声明
                .subject(userPrincipal.getUsername())
                // aud – 受众声明
                .audience("aud")
                // exp – 过期时间
                .expirationTime(new Date(System.currentTimeMillis() + (rememberMe ? jwtProperties.getRemember() : jwtProperties.getTtl())))
                // nbf – 声明不可用
                .notBeforeTime(new Date())
                // iat – 发出的声明
                .issueTime(new Date())
                // jti – JWT ID 声明
                .jwtID(userPrincipal.getId().toString())
                .claim(ROLES, userPrincipal.getRoles())
                .claim(AUTHORITIES, userPrincipal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .claim(DATA_POWERS, userPrincipal.getDataPowers())
                .claim(IS_ENABLED, userPrincipal.getIsEnabled())
                .claim(DEPT_ID, userPrincipal.getDeptId())
                .claim(ORG_ID, userPrincipal.getOrgId())
                .build();

        SignedJWT signedJwt = new SignedJWT(jwsHeader, claimsSet);
        try {
            signedJwt.sign(new MACSigner(jwtProperties.getKey()));
        } catch (JOSEException e) {
            throw new ResultException(ResultStatus.JOSE_Exception);
        }

        return signedJwt.serialize();


    }

    public String createJwt(@NotNull Authentication authentication, @NotNull Boolean rememberMe) {
        return createJwt((UserPrincipal) authentication.getPrincipal(), rememberMe);
    }


    /**
     * 设置JWT过期
     */
    @SuppressWarnings({"unused", "EmptyMethod"})
    public void invalidateJwt(@NotEmpty String authorization) {
    }


    public UserPrincipal getUserPrincipal(@NotEmpty String authorization) {
        String jwt = authorization.startsWith(BEARER) ? authorization.substring("Bearer ".length()) : authorization;
        JWTClaimsSet claimsSet = this.parseJwt(jwt);
        UserPrincipal userPrincipal = new UserPrincipal();
        userPrincipal.setId(Long.valueOf(claimsSet.getJWTID()));
        userPrincipal.setUsername(claimsSet.getSubject());

        try {
            userPrincipal.setDeptId(claimsSet.getLongClaim(DEPT_ID));
            userPrincipal.setOrgId(claimsSet.getLongClaim(ORG_ID));
            userPrincipal.setIsEnabled(claimsSet.getBooleanClaim(IS_ENABLED));
            userPrincipal.setRoles(new HashSet<>(claimsSet.getStringListClaim(ROLES)));
            userPrincipal.setAuthorities(claimsSet.getStringListClaim(AUTHORITIES).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet()));
            //noinspection unchecked
            userPrincipal.setDataPowers(new HashSet<>((Collection<Long>) claimsSet.getClaim(DATA_POWERS)));
        } catch (ParseException e) {
            throw new ResultException(ResultStatus.UNAUTHORIZED);
        }

        return userPrincipal;
    }

    private JWTClaimsSet parseJwt(String jwt) {


        try {
            SignedJWT signedJwt = SignedJWT.parse(jwt);
            MACVerifier verifier = new MACVerifier(jwtProperties.getKey());

            this.verify(signedJwt, verifier);
            return signedJwt.getJWTClaimsSet();
        } catch (JOSEException | ParseException e) {
            throw new ResultException(ResultStatus.UNAUTHORIZED);
        }
    }


    private void verify(SignedJWT signedJwt, MACVerifier verifier) throws ParseException, JOSEException {

        if (!signedJwt.verify(verifier)) {
            throw new ResultException(ResultStatus.UNAUTHORIZED);
        }
        JWTClaimsSet claimsSet = signedJwt.getJWTClaimsSet();

        // throw new ResultException(ResultStatus.UNAUTHORIZED);
        final Date now = new Date();
        final Date exp = claimsSet.getExpirationTime();

        if (exp == null || !DateUtils.isAfter(exp, now, MAX_CLOCK_SKEW_SECONDS)) {
            throw new ResultException(ResultStatus.EXPIRED_TOKEN);
        }

        final Date nbf = claimsSet.getNotBeforeTime();
        if (nbf == null || !DateUtils.isBefore(nbf, now, MAX_CLOCK_SKEW_SECONDS)) {
            throw new ResultException(ResultStatus.TOKEN_BEFORE_USE_TIME);
        }
    }
}