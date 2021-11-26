package plus.wcj.heifer.common.security.jwt;


import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.util.DateUtils;
import plus.wcj.heifer.boot.common.exception.ResultException;
import plus.wcj.heifer.boot.common.exception.ResultStatusEnum;
import plus.wcj.heifer.boot.common.security.properties.JwtProperties;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacAccountDto;
import plus.wcj.heifer.boot.common.security.userdetails.dto.RbacAccountManageDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

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
    public static final String TENANT_ID = "ti";
    public static final String ALL_POWER = "ap";
    public static final String ALL_AUTHORITY = "aa";

    public static final String BEARER = "Bearer ";

    public static final Long MAX_CLOCK_SKEW_SECONDS = 60L;
    private final JwtProperties jwtProperties;


    /** 创建JWS头，设置签名算法和类型 */
    private final JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256)
            // 设置类型 ( typ ) 参数
            .type(JOSEObjectType.JWT)
            // 设置密钥 ID ( kid ) 参数。
            // .keyID("kid")
            .build();

    public String createJwt(RbacAccountDto rbacAccountDto, @NotNull Boolean rememberMe) {
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                // iss – 发行人声明
                .issuer("iss")
                //sub – 主题声明
                .subject(rbacAccountDto.getUsername())
                // aud – 受众声明
                .audience("aud")
                // exp – 过期时间
                .expirationTime(new Date(System.currentTimeMillis() + (rememberMe ? this.jwtProperties.getRemember() : this.jwtProperties.getTtl())))
                // nbf – 声明不可用
                .notBeforeTime(new Date())
                // iat – 发出的声明
                .issueTime(new Date())
                // jti – JWT ID 声明
                .jwtID(rbacAccountDto.getId().toString())

                .claim(DEPT_ID, rbacAccountDto.getAccountManage().getRbacDeptId())
                .claim(TENANT_ID, rbacAccountDto.getAccountManage().getRbacTenantId())

                .build();
        SignedJWT signedJwt = new SignedJWT(this.jwsHeader, claimsSet);
        try {
            signedJwt.sign(new MACSigner(this.jwtProperties.getKey()));
        } catch (JOSEException e) {
            throw new ResultException(ResultStatusEnum.JOSE_EXCEPTION);
        }

        return signedJwt.serialize();
    }


    /**
     * 设置JWT过期
     */
    @SuppressWarnings({"unused", "EmptyMethod"})
    public void invalidateJwt(@NotEmpty String authorization) {
    }


    public RbacAccountDto getAccount(@NotEmpty String authorization) {
        String jwt = authorization.startsWith(BEARER) ? authorization.substring("Bearer ".length()) : authorization;
        JWTClaimsSet claimsSet = this.parseJwt(jwt);

        // TODO: 2021/11/23 changjin wei(魏昌进)


        RbacAccountManageDto accountManageDto = new RbacAccountManageDto();
        try {
            accountManageDto.setRbacTenantId(claimsSet.getLongClaim(TENANT_ID));
            accountManageDto.setRbacDeptId(claimsSet.getLongClaim(DEPT_ID));
        } catch (ParseException e) {
            throw new ResultException(ResultStatusEnum.UNAUTHORIZED);
        }
        RbacAccountDto accountDto = new RbacAccountDto();
        accountDto.setId(Long.valueOf(claimsSet.getJWTID()));
        accountDto.setUsername(claimsSet.getSubject());
        accountDto.setAccountManage(accountManageDto);
        return accountDto;
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