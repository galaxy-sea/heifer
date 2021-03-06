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

package plus.wcj.heifer.boot.mvc;


import plus.wcj.heifer.metadata.bean.BindObjectError;
import plus.wcj.heifer.metadata.bean.Result;
import plus.wcj.heifer.metadata.exception.ResultException;
import plus.wcj.heifer.metadata.exception.ResultStatus;
import plus.wcj.heifer.metadata.exception.ResultStatusEnum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

import java.nio.file.AccessDeniedException;

/**
 * @author changjin wei(?????????)
 * @since 2021/7/8
 */
@Slf4j
@RequiredArgsConstructor

@ControllerAdvice
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ResultExceptionHandler {

    private final MessageSource messageSource;

    @SuppressWarnings("unused")
    protected <T> ResponseEntity<Result<?>> handleExceptionInternal(ResultStatus resultStatus, Exception ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(resultStatus, null, null, ex, headers, request);
    }


    @SuppressWarnings({"unused", "MethodWithTooManyParameters"})
    protected <T> ResponseEntity<Result<?>> handleExceptionInternal(ResultStatus resultStatus, T data, Object[] ages, Exception ex, HttpHeaders headers, WebRequest request) {
        String message = messageSource.getMessage(resultStatus.getClass().getName() + '.' + resultStatus.name(), ages, resultStatus.getMessage(), request.getLocale());
        Result<T> body = Result.of(resultStatus.getCode(), message, data);
        log.error("{}\nmessage\n{}\n{}", ex.getClass(), message, ex.getMessage());
        if (log.isDebugEnabled()) {
            log.debug("?????????????????????????????????", ex);
        }
        return new ResponseEntity<>(body, headers, resultStatus.getHttpStatus());
    }


    /**
     * ???????????????Spring MVC???????????????
     *
     * @param ex the target exception
     * @param request the current request
     * @return ResponseEntity
     */
    @ExceptionHandler({

            Exception.class,

            // ???????????????
            ResultException.class,

            // ????????????  ???????????????????????????????????????????????????????????????????????????if????????????????????????
            // validation
            BindException.class,
            // security
            // BadCredentialsException.class,
            AccessDeniedException.class,

            // spring ????????????
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

        // TODO: 2019/10/05 galaxy ??????????????????????????????????????????
        return this.handleException(ex, headers, request);
    }


    /**
     * ?????????????????????
     * @param ex {@link ResultException}
     * @param headers ????????????headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleResultException(ResultException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ex.getResultStatus(), null, ex.getAges(), ex, headers, request);
    }

    /**
     * ????????????
     * @param ex {@link AccessDeniedException}
     * @param headers ????????????headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    private ResponseEntity<Result<?>> handleAccessDeniedException(AccessDeniedException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.FORBIDDEN, ex, headers, request);
    }


    /**
     * validation??????
     * @param ex {@link BindException}
     * @param headers ????????????headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleBindException(BindException ex, HttpHeaders headers, WebRequest request) {
        FieldError fieldError = ex.getFieldError();
        BindObjectError bindObjectError = new BindObjectError(fieldError.getDefaultMessage(), fieldError.getField(), fieldError.getCode());
        return this.handleExceptionInternal(ResultStatusEnum.BAD_REQUEST, bindObjectError, null, ex, headers, request);
    }

    /**
     * ??????????????????
     * @param ex {@link Exception}
     * @param headers ????????????headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleException(Exception ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.INTERNAL_SERVER_ERROR, ex, headers, request);
    }

    /**
     * ??????????????????????????????????????????????????????????????????
     * @param ex {@link HttpRequestMethodNotSupportedException}
     * @param headers ????????????headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.METHOD_NOT_ALLOWED, ex, headers, request);
    }

    /**
     * ???????????? POST???PUT ??? PATCH ???????????????????????????????????????????????????????????????
     * @param ex {@link HttpMediaTypeNotSupportedException}
     * @param headers ????????????headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.UNSUPPORTED_MEDIA_TYPE, ex, headers, request);
    }

    /**
     * ??????????????????????????????????????????????????????????????????????????????
     * @param ex {@link HttpMediaTypeNotAcceptableException}
     * @param headers ????????????headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.NOT_ACCEPTABLE, ex, headers, request);
    }

    /**
     * ????????? URL ????????? URI ??????????????????@RequestMapping???????????????????????????????????????????????? ????????????????????? URI ????????????????????????????????????????????????????????????
     * @param ex {@link MissingPathVariableException}
     * @param headers ????????????headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleMissingPathVariableException(MissingPathVariableException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.INTERNAL_SERVER_ERROR, ex, headers, request);
    }

    /**
     * ??????????????????
     * @param ex {@link MissingServletRequestParameterException}
     * @param headers ????????????headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.BAD_REQUEST, ex, headers, request);
    }

    /**
     * ???????????????????????????????????????????????????????????????????????????
     * @param ex {@link ServletRequestBindingException}
     * @param headers ????????????headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.BAD_REQUEST, ex, headers, request);
    }

    /**
     * ???????????? bean ?????????????????????????????????????????????????????????
     * @param ex {@link ConversionNotSupportedException}
     * @param headers ????????????headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleConversionNotSupportedException(ConversionNotSupportedException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.INTERNAL_SERVER_ERROR, ex, headers, request);
    }

    /**
     * ???????????? bean ???????????????????????????????????????
     * @param ex {@link TypeMismatchException}
     * @param headers ????????????headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleTypeMismatchException(TypeMismatchException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.BAD_REQUEST, ex, headers, request);
    }

    /**
     * ???HttpMessageConverter.read??????????????????HttpMessageConverter???????????????
     * @param ex {@link HttpMessageNotReadableException}
     * @param headers ????????????headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.BAD_REQUEST, ex, headers, request);
    }

    /**
     * ???HttpMessageConverter.write??????????????????HttpMessageConverter????????????
     * @param ex {@link HttpMessageNotWritableException}
     * @param headers ????????????headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleHttpMessageNotWritableException(HttpMessageNotWritableException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.INTERNAL_SERVER_ERROR, ex, headers, request);
    }

    /**
     * ???????????????????????????????????????multipart/form-data??????????????????????????????.
     * ???????????????????????????????????????/???????????????????????????????????????????????????????????????????????? Web ?????????????????????????????????????????????????????????????????????MultipartResolver
     * @param ex {@link MissingServletRequestPartException}
     * @param headers ????????????headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleMissingServletRequestPartException(MissingServletRequestPartException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.BAD_REQUEST, ex, headers, request);
    }

    /**
     * ??????????????????
     * @param ex {@link NoHandlerFoundException}
     * @param headers ????????????headers
     * @param request the current request
     * @return new {@link ResponseEntity}
     */
    protected ResponseEntity<Result<?>> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.NOT_FOUND, ex, headers, request);
    }

    /**
     * ????????????????????????????????????.
     * ?????????????????????????????????DeferredResultProcessingInterceptor???CallableProcessingInterceptor????????? MVC Java ????????? MVC XML ???????????????????????????RequestMappingHandlerAdapter?????????????????????
     * @param ex {@link AsyncRequestTimeoutException}
     * @param headers ????????????headers
     * @param webRequest webRequest
     * @return new {@link ResponseEntity} ResponseEntity
     */
    protected ResponseEntity<Result<?>> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, WebRequest webRequest) {
        return this.handleExceptionInternal(ResultStatusEnum.SERVICE_UNAVAILABLE, ex, headers, webRequest);
    }

}
