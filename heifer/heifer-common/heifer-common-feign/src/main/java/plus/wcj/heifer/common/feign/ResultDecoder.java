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

package plus.wcj.heifer.common.feign;

import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import plus.wcj.heifer.metadata.annotation.ResponseBodyResult;
import plus.wcj.heifer.metadata.bean.Result;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/8
 */
public class ResultDecoder implements Decoder {
    private final Decoder decoder;

    public ResultDecoder(Decoder decoder) {
        this.decoder = decoder;
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {
        Method method = response.request().requestTemplate().methodMetadata().method();
        boolean isResult = method.getReturnType() != Result.class && method.isAnnotationPresent(ResponseBodyResult.class);
        if (isResult) {
            ParameterizedTypeImpl resultType = new ParameterizedTypeImpl(Result.class, new Type[]{type});
            Result<?> result = (Result<?>) this.decoder.decode(response, resultType);
            return result.getData();
        }
        return this.decoder.decode(response, type);
    }
}
