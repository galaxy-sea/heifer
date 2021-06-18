package plus.wcj.heifer.boot.common.security;


import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.proc.BadJWTException;
import com.nimbusds.jwt.util.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import plus.wcj.heifer.boot.common.exception.ResultException;
import plus.wcj.heifer.boot.common.exception.ResultStatus;
import plus.wcj.heifer.boot.common.security.properties.JwtProperties;
import plus.wcj.heifer.boot.common.security.userdetails.dto.UserPrincipal;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * JWT 工具类
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-07 13:42
 */
@EnableConfigurationProperties(JwtProperties.class)
@Configuration
@Slf4j
public class JwtUtilCopy {

    @Autowired
    private JwtProperties jwtProperties;

    public String createJWT(UserPrincipal userPrincipal, Boolean rememberMe) {
        return createJWT(rememberMe, userPrincipal.getId(), userPrincipal.getUsername(), userPrincipal.getRoles(), userPrincipal.getAuthorities());
    }

    public String createJWT(Authentication authentication, Boolean rememberMe) {
        return createJWT((UserPrincipal) authentication.getPrincipal(), rememberMe);
    }

    private String createJWT(Boolean rememberMe, Long id, String subject, List<String> roles, Collection<? extends GrantedAuthority> authorities) {
        //创建JWS头，设置签名算法和类型
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
                .subject(subject)
                // aud – 受众声明
                .audience("aud")
                // exp – 过期时间
                .expirationTime(new Date(System.currentTimeMillis() + 10000000))
                // nbf – 声明不可用
                .notBeforeTime(new Date())
                // iat – 发出的声明
                .issueTime(new Date())
                // jti – JWT ID 声明
                .jwtID(id.toString())
                .claim("my", "my")
                .build();

        return null;
    }


    public UserPrincipal getUserPrincipal(String jwt) {
        JWTClaimsSet claimsSet = this.parseJWT(jwt);
        UserPrincipal userPrincipal = new UserPrincipal();
        // TODO: 2021/6/18 changjin wei(魏昌进)


        return userPrincipal;
    }

    private JWTClaimsSet parseJWT(String jwt) {
        SignedJWT signedJwt = SignedJWT.parse(jwt);
        MACVerifier verifier = new MACVerifier(jwtProperties.getKey());

        this.invalidateJWT(signedJwt, verifier);

        return signedJwt.getJWTClaimsSet();
    }


    private void invalidateJWT(SignedJWT signedJwt, MACVerifier verifier) {

        if (!signedJwt.verify(verifier)) {
            throw new ResultException(ResultStatus.UNAUTHORIZED);
        }
        JWTClaimsSet claimsSet = signedJwt.getJWTClaimsSet();
        final Date now = new Date();

        final Date exp = claimsSet.getExpirationTime();

        if (exp == null || !DateUtils.isAfter(exp, now, 60)) {
            throw new BadJWTException("Expired JWT");
        }

        final Date nbf = claimsSet.getNotBeforeTime();
        if (nbf == null || !DateUtils.isBefore(nbf, now, 60)) {
            throw new BadJWTException("JWT before use time");
        }
    }


}
