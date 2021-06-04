package plus.wcj.heifer.boot;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import org.junit.jupiter.api.Test;

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

}
