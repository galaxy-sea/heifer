package plus.wcj.heifer.boot;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.util.DateUtils;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/6/4
 */
// @SpringBootTest
public class JwtTest {

    @Test
    public void test() throws JOSEException {

        //创建JWS头，设置签名算法和类型
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256).type(JOSEObjectType.JWT).build();
        //将负载信息封装到Payload中
        Payload payload = new Payload("!23");
        //创建JWS对象
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);
        //创建HMAC签名器
        JWSSigner jwsSigner = new MACSigner("12345678901234567890123456798012");
        //签名
        jwsObject.sign(jwsSigner);

        String serialize = jwsObject.serialize();
        System.out.println(serialize);
    }


    @Test
    public void test1() throws JOSEException {

        //创建JWS头，设置签名算法和类型
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256)
                .type(JOSEObjectType.JWT)
                .keyID("hahahahah")
                .build();

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .issuer("iss")
                .subject("sub")
                .audience("aud")
                .expirationTime(new Date(System.currentTimeMillis() + 10000000))
                .notBeforeTime(new Date())
                .issueTime(new Date())
                .jwtID("jti")
                .claim("my", "my")
                .build();

        SignedJWT signedJWT = new SignedJWT(jwsHeader, claimsSet);

        signedJWT.sign(new MACSigner("12345678901234567890123456798012"));

        System.out.println(signedJWT.serialize());
    }



    @Test
    public void test2() throws JOSEException, ParseException {
        // eyJraWQiOiJoYWhhaGFoYWgiLCJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdWIiLCJhdWQiOiJhdWQiLCJuYmYiOjE2MjM4MjE0OTUsImlzcyI6ImlzcyIsImV4cCI6MTYyMzgzMTQ5NSwibXkiOiJteSIsImlhdCI6MTYyMzgyMTQ5NSwianRpIjoianRpIn0.ExX32qEBlx6NWcafO4uOh7alxyQR7mFJPAJMaQ-2BYY

        SignedJWT jwt = SignedJWT.parse("eyJraWQiOiJoYWhhaGFoYWgiLCJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdWIiLCJhdWQiOiJhdWQiLCJuYmYiOjE2MjM4MjM4MjYsImlzcyI6ImlzcyIsImV4cCI6MTYyMzgzMzgyNiwibXkiOiJteSIsImlhdCI6MTYyMzgyMzgyNiwianRpIjoianRpIn0.rU7rHa0p8iwyfk2RN8y5gQkzRJ1kKXFwaK1gR8YMHsI");

        MACVerifier verifier = new MACVerifier("12345678901234567890123456798012");
        //校验是否有效

        System.out.println(jwt.verify(verifier));
        // //校验超时
        Date exp = jwt.getJWTClaimsSet().getExpirationTime();
        System.out.println(DateUtils.isBefore(exp, new Date(), 60));



    }
}
