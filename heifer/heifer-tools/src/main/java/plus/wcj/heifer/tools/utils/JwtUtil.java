/*
 * Copyright 2021-2022 the original author or authors.
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

package plus.wcj.heifer.tools.utils;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.util.DateUtils;
import plus.wcj.heifer.metadata.exception.ResultException;
import plus.wcj.heifer.metadata.exception.ResultStatusEnum;

import java.text.ParseException;
import java.util.Date;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/12/22
 */
public final class JwtUtil {

    public static final String BEARER = "Bearer ";

    public static final Long MAX_CLOCK_SKEW_SECONDS = 60L;

    private static final JWSHeader JWS_HEADER = new JWSHeader.Builder(JWSAlgorithm.HS256)
            // 设置类型 ( typ ) 参数
            .type(JOSEObjectType.JWT)
            // 设置密钥 ID ( kid ) 参数。
            // .keyID("kid")
            .build();

    private JwtUtil() {
    }

    /**
     * 序列化 claimsSet
     *
     * @param claimsSet {@link JWTClaimsSet}
     * @param key 密钥
     *
     * @return jwt token
     */
    public static String createJwt(JWTClaimsSet claimsSet, String key) {
        SignedJWT signedJwt = new SignedJWT(JWS_HEADER, claimsSet);
        try {
            signedJwt.sign(new MACSigner(key));
        } catch (JOSEException e) {
            throw new ResultException(ResultStatusEnum.JOSE_EXCEPTION);
        }

        return signedJwt.serialize();
    }


    /**
     * 反序列化
     *
     * @param authorization token
     * @param key 密钥
     *
     * @return {@link JWTClaimsSet}
     */
    public static JWTClaimsSet parseAuthorization(String authorization, String key) {
        String jwt = authorization.startsWith(BEARER) ? authorization.substring(BEARER.length()) : authorization;
        return parseJwt(jwt, key);
    }


    /**
     * 反序列化
     *
     * @param jwt token
     * @param key 密钥
     *
     * @return JWTClaimsSet
     */
    public static JWTClaimsSet parseJwt(String jwt, String key) {
        try {
            SignedJWT signedJwt = SignedJWT.parse(jwt);
            MACVerifier verifier = new MACVerifier(key);
            verify(signedJwt, verifier);
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
    public static void verify(SignedJWT signedJwt, MACVerifier verifier) throws ParseException, JOSEException {

        if (!signedJwt.verify(verifier)) {
            throw new ResultException(ResultStatusEnum.UNAUTHORIZED);
        }
        JWTClaimsSet claimsSet = signedJwt.getJWTClaimsSet();

        final Date now = new Date();
        final Date exp = claimsSet.getExpirationTime();

        if (exp == null || DateUtils.isBefore(exp, now, MAX_CLOCK_SKEW_SECONDS)) {
            throw new ResultException(ResultStatusEnum.EXPIRED_TOKEN);
        }

        final Date nbf = claimsSet.getNotBeforeTime();
        if (nbf == null || DateUtils.isAfter(nbf, now, MAX_CLOCK_SKEW_SECONDS)) {
            throw new ResultException(ResultStatusEnum.TOKEN_BEFORE_USE_TIME);
        }
    }

    public static boolean isValidExp(Date exp, Date now) {
        return exp != null && DateUtils.isAfter(exp, now, MAX_CLOCK_SKEW_SECONDS);
    }
}
