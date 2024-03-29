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

package plus.wcj.heifer.boot.web;

import jakarta.validation.ConstraintViolationException;
import plus.wcj.heifer.metadata.bean.BindObjectError;
import plus.wcj.heifer.metadata.bean.Result;
import plus.wcj.heifer.metadata.exception.ResultException;
import plus.wcj.heifer.metadata.exception.ResultStatus;
import plus.wcj.heifer.metadata.exception.ResultStatusEnum;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.AccessDeniedException;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/7/8
 */
@ControllerAdvice
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ResultExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ResultExceptionHandler.class);


    private final MessageSource messageSource;

    public ResultExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @SuppressWarnings("unused")
    protected <T> ResponseEntity<Result<?>> handleExceptionInternal(ResultStatus resultStatus, Exception ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(resultStatus, null, null, ex, headers, request);
    }


    @SuppressWarnings({"unused", "MethodWithTooManyParameters"})
    protected <T> ResponseEntity<Result<?>> handleExceptionInternal(ResultStatus resultStatus, T data, Object[] ages, Exception ex, HttpHeaders headers, WebRequest request) {
        String message = messageSource.getMessage(resultStatus.getClass().getName() + '.' + resultStatus.name(), ages, resultStatus.getMessage(), request.getLocale());
        Result<T> body = Result.of(resultStatus.getCode(), message, data);
        logger.error("{}\nmessage\n{}\n{}", ex.getClass(), message, ex.getMessage());
        if (logger.isDebugEnabled()) {
            logger.debug("小可爱，注意检查代码哦", ex);
        }
        return new ResponseEntity<>(body, headers, resultStatus.getHttpStatus());
    }


    /**
     * 提供对标准Spring MVC异常的处理
     *
     * @param ex the target exception
     * @param request the current request
     * @return ResponseEntity
     */
    @ExceptionHandler({

            Exception.class,

            // 自定义异常
            ResultException.class,

            // 已知异常  虽然在这里重新添加一遍很傻逼，但是也许有个小可爱在if写了一样的代码呐
            // validation
            BindException.class,
            ConstraintViolationException.class,

            // security
            // BadCredentialsException.class,
            AccessDeniedException.class,

            // spring 默认异常
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpMediaTypeNotAcceptableException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMessageNotWritableException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestPartException.class,
            NoHandlerFoundException.class,
            AsyncRequestTimeoutException.class

    })
    public final ResponseEntity<Result<?>> exceptionHandler(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        if (ex instanceof ResultException resultException) {
            return this.handleResultException(resultException, headers, request);
        }
        if (ex instanceof BindException exception) {
            return this.handleBindException(exception, headers, request);
        }
        if (ex instanceof ConstraintViolationException exception) {
            return this.handleConstraintViolationException(exception, headers, request);
        }
        if (ex instanceof AccessDeniedException accessDeniedException) {
            return this.handleAccessDeniedException(accessDeniedException, headers, request);
        }
        if (ex instanceof HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {
            return this.handleHttpRequestMethodNotSupportedException(httpRequestMethodNotSupportedException, headers, request);
        }
        if (ex instanceof HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException) {
            return this.handleHttpMediaTypeNotSupportedException(httpMediaTypeNotSupportedException, headers, request);
        }
        if (ex instanceof HttpMediaTypeNotAcceptableException httpMediaTypeNotAcceptableException) {
            return this.handleHttpMediaTypeNotAcceptableException(httpMediaTypeNotAcceptableException, headers, request);
        }
        if (ex instanceof MissingPathVariableException missingPathVariableException) {
            return this.handleMissingPathVariableException(missingPathVariableException, headers, request);
        }
        if (ex instanceof MissingServletRequestParameterException missingServletRequestParameterException) {
            return this.handleMissingServletRequestParameterException(missingServletRequestParameterException, headers, request);
        }
        if (ex instanceof ServletRequestBindingException servletRequestBindingException) {
            return this.handleServletRequestBindingException(servletRequestBindingException, headers, request);
        }
        if (ex instanceof ConversionNotSupportedException conversionNotSupportedException) {
            return this.handleConversionNotSupportedException(conversionNotSupportedException, headers, request);
        }
        if (ex instanceof TypeMismatchException typeMismatchException) {
            return this.handleTypeMismatchException(typeMismatchException, headers, request);
        }
        if (ex instanceof HttpMessageNotReadableException httpMessageNotReadableException) {
            return this.handleHttpMessageNotReadableException(httpMessageNotReadableException, headers, request);
        }
        if (ex instanceof HttpMessageNotWritableException httpMessageNotWritableException) {
            return this.handleHttpMessageNotWritableException(httpMessageNotWritableException, headers, request);
        }
        if (ex instanceof MissingServletRequestPartException missingServletRequestPartException) {
            return this.handleMissingServletRequestPartException(missingServletRequestPartException, headers, request);
        }
        if (ex instanceof NoHandlerFoundException noHandlerFoundException) {
            return this.handleNoHandlerFoundException(noHandlerFoundException, headers, request);
        }
        if (ex instanceof AsyncRequestTimeoutException asyncRequestTimeoutException) {
            return this.handleAsyncRequestTimeoutException(asyncRequestTimeoutException, headers, request);
        }

        // TODO: 2019/10/05 galaxy 这里可以自定义其他的异常拦截
        return this.handleException(ex, headers, request);
    }

    private ResponseEntity<Result<?>> handleConstraintViolationException(ConstraintViolationException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.BAD_REQUEST, ex, headers, request);
    }

    /**
     * 自定义异常处理
     * @param ex {@link ResultException}
     * @param headers 要返回的headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleResultException(ResultException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ex.getResultStatus(), null, ex.getAges(), ex, headers, request);
    }

    /**
     * 拒绝访问
     * @param ex {@link AccessDeniedException}
     * @param headers 要返回的headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    private ResponseEntity<Result<?>> handleAccessDeniedException(AccessDeniedException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.FORBIDDEN, ex, headers, request);
    }


    /**
     * validation校验
     * @param ex {@link BindException}
     * @param headers 要返回的headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleBindException(BindException ex, HttpHeaders headers, WebRequest request) {
        FieldError fieldError = ex.getFieldError();
        BindObjectError bindObjectError = new BindObjectError(fieldError.getDefaultMessage(), fieldError.getField(), fieldError.getCode());
        return this.handleExceptionInternal(ResultStatusEnum.BAD_REQUEST, bindObjectError, null, ex, headers, request);
    }

    /**
     * 所有未知异常
     * @param ex {@link Exception}
     * @param headers 要返回的headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleException(Exception ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.INTERNAL_SERVER_ERROR, ex, headers, request);
    }

    /**
     * 当请求处理程序不支持特定的请求方法时抛出异常
     * @param ex {@link HttpRequestMethodNotSupportedException}
     * @param headers 要返回的headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.METHOD_NOT_ALLOWED, ex, headers, request);
    }

    /**
     * 当客户端 POST、PUT 或 PATCH 请求处理程序不支持的类型的内容时引发异常。
     * @param ex {@link HttpMediaTypeNotSupportedException}
     * @param headers 要返回的headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.UNSUPPORTED_MEDIA_TYPE, ex, headers, request);
    }

    /**
     * 当请求处理程序无法生成客户端可接受的响应时抛出异常。
     * @param ex {@link HttpMediaTypeNotAcceptableException}
     * @param headers 要返回的headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.NOT_ACCEPTABLE, ex, headers, request);
    }

    /**
     * 指示从 URL 提取的 URI 变量中不存在@RequestMapping方法的方法参数中预期的路径变量。 通常，这意味着 URI 模板与方法参数上声明的路径变量名称不匹配
     * @param ex {@link MissingPathVariableException}
     * @param headers 要返回的headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleMissingPathVariableException(MissingPathVariableException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.INTERNAL_SERVER_ERROR, ex, headers, request);
    }

    /**
     * 表示缺少参数
     * @param ex {@link MissingServletRequestParameterException}
     * @param headers 要返回的headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.BAD_REQUEST, ex, headers, request);
    }

    /**
     * 致命绑定异常，当我们想将绑定异常视为不可恢复时抛出
     * @param ex {@link ServletRequestBindingException}
     * @param headers 要返回的headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.BAD_REQUEST, ex, headers, request);
    }

    /**
     * 当无法为 bean 属性找到合适的编辑器或转换器时抛出异常
     * @param ex {@link ConversionNotSupportedException}
     * @param headers 要返回的headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleConversionNotSupportedException(ConversionNotSupportedException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.INTERNAL_SERVER_ERROR, ex, headers, request);
    }

    /**
     * 尝试设置 bean 属性时因类型不匹配引发异常
     * @param ex {@link TypeMismatchException}
     * @param headers 要返回的headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleTypeMismatchException(TypeMismatchException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.BAD_REQUEST, ex, headers, request);
    }

    /**
     * 当HttpMessageConverter.read方法失败时由HttpMessageConverter实现抛出。
     * @param ex {@link HttpMessageNotReadableException}
     * @param headers 要返回的headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.BAD_REQUEST, ex, headers, request);
    }

    /**
     * 当HttpMessageConverter.write方法失败时由HttpMessageConverter实现抛出
     * @param ex {@link HttpMessageNotWritableException}
     * @param headers 要返回的headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleHttpMessageNotWritableException(HttpMessageNotWritableException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.INTERNAL_SERVER_ERROR, ex, headers, request);
    }

    /**
     * 当无法找到由其名称标识的“multipart/form-data”请求的一部分时引发.
     * 这可能是因为请求不是多部分/表单数据请求，因为该部分不存在于请求中，或者因为 Web 应用程序没有正确配置来处理多部分请求，例如没有MultipartResolver
     * @param ex {@link MissingServletRequestPartException}
     * @param headers 要返回的headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleMissingServletRequestPartException(MissingServletRequestPartException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.BAD_REQUEST, ex, headers, request);
    }

    /**
     * 没有这个方法
     * @param ex {@link NoHandlerFoundException}
     * @param headers 要返回的headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.NOT_FOUND, ex, headers, request);
    }

    /**
     * 异步请求超时时抛出的异常.
     * 或者，应用程序可以注册DeferredResultProcessingInterceptor或CallableProcessingInterceptor以通过 MVC Java 配置或 MVC XML 命名空间或直接通过RequestMappingHandlerAdapter属性来处理超时
     * @param ex {@link AsyncRequestTimeoutException}
     * @param headers 要返回的headers
     * @param webRequest webRequest
     * @return new {@link ResponseEntity} ResponseEntity
     */
    protected ResponseEntity<Result<?>> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, WebRequest webRequest) {
        return this.handleExceptionInternal(ResultStatusEnum.SERVICE_UNAVAILABLE, ex, headers, webRequest);
    }

}
