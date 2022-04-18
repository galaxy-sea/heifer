package plus.wcj.heifer.boot.mvc;

import plus.wcj.heifer.metadata.annotation.ResultResponseBody;
import plus.wcj.heifer.metadata.bean.Result;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author changjin wei(魏昌进)
 * @date 2021/4/23
 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#configureMessageConverters(java.util.List)
 */
@RestControllerAdvice
@ControllerAdvice
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ResultResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    /** 判断类或者方法是否使用了 @ResponseResultBody */
    @SuppressWarnings("NullableProblems")
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ResultResponseBody.class) || returnType.hasMethodAnnotation(ResultResponseBody.class);
    }

    /** 当类或者方法使用了 @ResponseResultBody 就会调用这个方法 */
    @SuppressWarnings("NullableProblems")
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // return convert(convert(body), selectedConverterType);
        return convert(body);
    }

    private Result<?> convert(Object body) {
        if (body instanceof Result) {
            return (Result<?>) body;
        }
        return Result.success(body);
    }

    // TODO: 2022/4/18 changjin wei(魏昌进) bug 问题如果是xml呐？？？
    // private Object convert(Result<?> result, Class<? extends HttpMessageConverter<?>> selectedConverterType) {
    //     if (selectedConverterType == StringHttpMessageConverter.class && result.getData() instanceof String) {
    //         try {
    //             return objectMapper.writeValueAsString(result);
    //         } catch (JsonProcessingException e) {
    //             throw new ResultException();
    //         }
    //     }
    //     return result;
    // }

}

