package plus.wcj.heifer.common.feign;

import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import plus.wcj.heifer.metadata.annotation.ResultResponseBody;
import plus.wcj.heifer.metadata.bean.Result;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/8
 */
public class ResultDecoder implements Decoder {
    private Decoder decoder;

    public ResultDecoder(Decoder decoder) {
        this.decoder = decoder;
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        Method method = response.request().requestTemplate().methodMetadata().method();
        boolean isResult = method.getReturnType() != Result.class && method.isAnnotationPresent(ResultResponseBody.class);
        if (isResult) {
            ParameterizedTypeImpl resultType = ParameterizedTypeImpl.make(Result.class, new Type[]{type}, null);
            Result<?> result = (Result<?>) this.decoder.decode(response, resultType);
            return result.getData();
        }
        return this.decoder.decode(response, type);
    }
}
