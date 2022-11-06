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

package plus.wcj.heifer.boot.web.mvc;

import plus.wcj.heifer.metadata.bean.ParameterizedTypeImpl;
import plus.wcj.heifer.metadata.bean.Result;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/11/6
 */
public class RestTemplate extends org.springframework.web.client.RestTemplate {

    private static final Field responseTypeField;
    private static final Class<?> ResponseEntityResponseExtractorClass;

    private static final Field delegateField;

    public RestTemplate() {
    }

    public RestTemplate(ClientHttpRequestFactory requestFactory) {
        super(requestFactory);
    }

    public RestTemplate(List<HttpMessageConverter<?>> messageConverters) {
        super(messageConverters);
    }

    static {
        responseTypeField = ReflectionUtils.findField(HttpMessageConverterExtractor.class, "responseType");
        ReflectionUtils.makeAccessible(responseTypeField);
        try {
            ResponseEntityResponseExtractorClass = Class.forName("org.springframework.web.client.RestTemplate$ResponseEntityResponseExtractor");
            delegateField = ReflectionUtils.findField(ResponseEntityResponseExtractorClass, "delegate");
            ReflectionUtils.makeAccessible(delegateField);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor) throws RestClientException {
        Assert.notNull(url, "URI is required");
        Assert.notNull(method, "HttpMethod is required");
        ClientHttpResponse response = null;
        try {
            ClientHttpRequest request = createRequest(url, method);
            if (requestCallback != null) {
                requestCallback.doWithRequest(request);
            }
            response = request.execute();
            handleResponse(url, method, response);
            if (responseExtractor == null) {
                return null;
            }
            HttpHeaders headers = response.getHeaders();
            if (headers.containsKey(Result.RESULT_HEADER_KEY) && headers.get(Result.RESULT_HEADER_KEY).contains(Result.RESULT_HEADER_VALUE)) {
                return handleResult(response, responseExtractor);
            }
            return responseExtractor.extractData(response);
        } catch (IOException ex) {
            String resource = url.toString();
            String query = url.getRawQuery();
            resource = (query != null ? resource.substring(0, resource.indexOf('?')) : resource);
            throw new ResourceAccessException("I/O error on " + method.name() +
                                                      " request for \"" + resource + "\": " + ex.getMessage(), ex);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    private <T> T handleResult(ClientHttpResponse response, ResponseExtractor<T> responseExtractor) throws IOException {
        if (responseExtractor instanceof HttpMessageConverterExtractor converterExtractor) {
            Type responseTyp = (Type) ReflectionUtils.getField(responseTypeField, converterExtractor);
            if (responseTyp != Result.class) {
                ParameterizedTypeImpl resultType = new ParameterizedTypeImpl(Result.class, new Type[]{responseTyp});
                Result<T> result = new HttpMessageConverterExtractor<Result<T>>(resultType, getMessageConverters()).extractData(response);
                return result.getData();
            }
        }
        //org.springframework.web.client.RestTemplate$ResponseEntityResponseExtractor
        if (ResponseEntityResponseExtractorClass == responseExtractor.getClass()) {
            HttpMessageConverterExtractor<?> delegate = (HttpMessageConverterExtractor<?>) ReflectionUtils.getField(delegateField, responseExtractor);
            if (delegate != null) {
                Type responseTyp = (Type) ReflectionUtils.getField(responseTypeField, delegate);
                if (responseTyp != Result.class) {
                    ParameterizedTypeImpl resultType = new ParameterizedTypeImpl(Result.class, new Type[]{responseTyp});
                    Result<?> result = new HttpMessageConverterExtractor<Result<?>>(resultType, getMessageConverters()).extractData(response);
                    return (T) ResponseEntity.status(response.getRawStatusCode()).headers(response.getHeaders()).body(result.getData());
                }
            }
        }
        return responseExtractor.extractData(response);
    }

}
