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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import plus.wcj.heifer.boot.common.exception.ResultException;
import plus.wcj.heifer.boot.common.exception.ResultStatusEnum;
import plus.wcj.heifer.boot.common.security.properties.JwtProperties;
import plus.wcj.heifer.boot.common.security.userdetails.dto.UserPrincipal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.text.ParseException;
import java.util.Date;

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
@RequiredArgsConstructor
public class JwtUtil {

    public static final String ROLES = "r";
    public static final String PERMISSIONS = "p";
    public static final String DATA_POWERS = "d";
    public static final String IS_ENABLED = "ie";
    public static final String DEPT_ID = "di";
    public static final String ORG_ID = "oi";
    public static final String ALL_POWER = "ap";
    public static final String ALL_AUTHORITY = "aa";

    public static final String BEARER = "Bearer ";

    public static final Long MAX_CLOCK_SKEW_SECONDS = 60L;
    private final JwtProperties jwtProperties;


    /** 创建JWS头，设置签名算法和类型 */
    private final JWSHeader  jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256)
            // 设置类型 ( typ ) 参数
            .type(JOSEObjectType.JWT)
            // 设置密钥 ID ( kid ) 参数。
            // .keyID("kid")
            .build();

    public String createJwt(@NotNull UserPrincipal userPrincipal, @NotNull Boolean rememberMe) {

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                // iss – 发行人声明
                .issuer("iss")
                //sub – 主题声明
                .subject(userPrincipal.getUsername())
                // aud – 受众声明
                .audience("aud")
                // exp – 过期时间
                .expirationTime(new Date(System.currentTimeMillis() + (rememberMe ? this.jwtProperties.getRemember() : this.jwtProperties.getTtl())))
                // nbf – 声明不可用
                .notBeforeTime(new Date())
                // iat – 发出的声明
                .issueTime(new Date())
                // jti – JWT ID 声明
                .jwtID(userPrincipal.getId().toString())

                .claim(ROLES, userPrincipal.getRoles())
                .claim(PERMISSIONS, userPrincipal.getPermissions())
                .claim(DATA_POWERS, userPrincipal.getDataPowers())

                .claim(IS_ENABLED, userPrincipal.getIsEnabled())
                .claim(DEPT_ID, userPrincipal.getDeptId())
                .claim(ORG_ID, userPrincipal.getOrgId())

                .claim(ALL_POWER, userPrincipal.getAllPower())
                .claim(ALL_AUTHORITY, userPrincipal.getAllAuthority())

                .build();
        SignedJWT signedJwt = new SignedJWT(this.jwsHeader, claimsSet);
        try {
            signedJwt.sign(new MACSigner(this.jwtProperties.getKey()));
        } catch (JOSEException e) {
            throw new ResultException(ResultStatusEnum.JOSE_EXCEPTION);
        }

        return signedJwt.serialize();


    }

    public String createJwt(@NotNull Authentication authentication, @NotNull Boolean rememberMe) {
        return this.createJwt((UserPrincipal) authentication.getPrincipal(), rememberMe);
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
        try {
            return new UserPrincipal(
                    Long.valueOf(claimsSet.getJWTID()),
                    claimsSet.getSubject(),
                    null,
                    claimsSet.getBooleanClaim(IS_ENABLED),
                    claimsSet.getLongClaim(ORG_ID),
                    claimsSet.getLongClaim(DEPT_ID),
                    claimsSet.getBooleanClaim(ALL_POWER),
                    claimsSet.getBooleanClaim(ALL_AUTHORITY),
                    claimsSet.getStringClaim(ROLES),
                    claimsSet.getStringClaim(PERMISSIONS),
                    claimsSet.getStringClaim(DATA_POWERS),
                    null
            );
        } catch (ParseException e) {
            throw new ResultException(ResultStatusEnum.UNAUTHORIZED);
        }
    }

    private JWTClaimsSet parseJwt(String jwt) {


        try {
            SignedJWT signedJwt = SignedJWT.parse(jwt);
            MACVerifier verifier = new MACVerifier(this.jwtProperties.getKey());

            this.verify(signedJwt, verifier);
            return signedJwt.getJWTClaimsSet();
        } catch (JOSEException | ParseException e) {
            throw new ResultException(ResultStatusEnum.UNAUTHORIZED);
        }
    }


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