package plus.wcj.heifer.boot.common.mvc.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
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
import plus.wcj.heifer.boot.common.exception.ResultException;
import plus.wcj.heifer.boot.common.exception.ResultStatus;
import plus.wcj.heifer.boot.common.exception.ResultStatusEnum;

/**
 * @author changjin wei(?????????)
 * @since 2021/7/8
 */
@Slf4j
@ControllerAdvice
@RestControllerAdvice
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ResultResponseEntityExceptionHandler {

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
     */
    @ExceptionHandler({

            Exception.class,

            // ???????????????
            ResultException.class,

            // ????????????  ???????????????????????????????????????????????????????????????????????????if????????????????????????
            // validation
            BindException.class,
            // security
            BadCredentialsException.class,
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
        if (ex instanceof ResultException) {
            return this.handleResultException((ResultException) ex, headers, request);
        }
        if (ex instanceof BindException) {
            return this.handleBindException((BindException) ex, headers, request);
        }
        if (ex instanceof BadCredentialsException) {
            return this.handleBadCredentialsException((BadCredentialsException) ex, headers, request);
        }
        if (ex instanceof AccessDeniedException) {
            return this.handleAccessDeniedException((AccessDeniedException) ex, headers, request);
        }
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            return this.handleHttpRequestMethodNotSupportedException((HttpRequestMethodNotSupportedException) ex, headers, request);
        }
        if (ex instanceof HttpMediaTypeNotSupportedException) {
            return this.handleHttpMediaTypeNotSupportedException((HttpMediaTypeNotSupportedException) ex, headers, request);
        }
        if (ex instanceof HttpMediaTypeNotAcceptableException) {
            return this.handleHttpMediaTypeNotAcceptableException((HttpMediaTypeNotAcceptableException) ex, headers, request);
        }
        if (ex instanceof MissingPathVariableException) {
            return this.handleMissingPathVariableException((MissingPathVariableException) ex, headers, request);
        }
        if (ex instanceof MissingServletRequestParameterException) {
            return this.handleMissingServletRequestParameterException((MissingServletRequestParameterException) ex, headers, request);
        }
        if (ex instanceof ServletRequestBindingException) {
            return this.handleServletRequestBindingException((ServletRequestBindingException) ex, headers, request);
        }
        if (ex instanceof ConversionNotSupportedException) {
            return this.handleConversionNotSupportedException((ConversionNotSupportedException) ex, headers, request);
        }
        if (ex instanceof TypeMismatchException) {
            return this.handleTypeMismatchException((TypeMismatchException) ex, headers, request);
        }
        if (ex instanceof HttpMessageNotReadableException) {
            return this.handleHttpMessageNotReadableException((HttpMessageNotReadableException) ex, headers, request);
        }
        if (ex instanceof HttpMessageNotWritableException) {
            return this.handleHttpMessageNotWritableException((HttpMessageNotWritableException) ex, headers, request);
        }
        if (ex instanceof MissingServletRequestPartException) {
            return this.handleMissingServletRequestPartException((MissingServletRequestPartException) ex, headers, request);
        }
        if (ex instanceof NoHandlerFoundException) {
            return this.handleNoHandlerFoundException((NoHandlerFoundException) ex, headers, request);
        }
        if (ex instanceof AsyncRequestTimeoutException) {
            return this.handleAsyncRequestTimeoutException((AsyncRequestTimeoutException) ex, headers, request);
        }

        // TODO: 2019/10/05 galaxy ??????????????????????????????????????????
        return this.handleException(ex, headers, request);
    }


    /** ????????????????????? */
    protected ResponseEntity<Result<?>> handleResultException(ResultException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ex.getResultStatus(), null, ex.getAges(), ex, headers, request);
    }


    private ResponseEntity<Result<?>> handleAccessDeniedException(AccessDeniedException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.FORBIDDEN, ex, headers, request);
    }

    private ResponseEntity<Result<?>> handleBadCredentialsException(BadCredentialsException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.INCORRECT_USERNAME_OR_PASSWORD, ex, headers, request);
    }


    /** validation?????? */
    protected ResponseEntity<Result<?>> handleBindException(BindException ex, HttpHeaders headers, WebRequest request) {
        FieldError fieldError = ex.getFieldError();
        BindObjectError bindObjectError = new BindObjectError(fieldError.getDefaultMessage(), fieldError.getField(), fieldError.getCode());
        return this.handleExceptionInternal(ResultStatusEnum.BAD_REQUEST, bindObjectError, null, ex, headers, request);
    }

    /** ?????????????????? */
    protected ResponseEntity<Result<?>> handleException(Exception ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.INTERNAL_SERVER_ERROR, ex, headers, request);
    }

    /** ?????????????????????????????????????????????????????????????????? */
    protected ResponseEntity<Result<?>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.METHOD_NOT_ALLOWED, ex, headers, request);
    }

    /** ???????????? POST???PUT ??? PATCH ??????????????????????????????????????????????????????????????? */
    protected ResponseEntity<Result<?>> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.UNSUPPORTED_MEDIA_TYPE, ex, headers, request);
    }

    /** ?????????????????????????????????????????????????????????????????????????????? */
    protected ResponseEntity<Result<?>> handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.NOT_ACCEPTABLE, ex, headers, request);
    }

    /** ????????? URL ????????? URI ??????????????????@RequestMapping???????????????????????????????????????????????? ????????????????????? URI ???????????????????????????????????????????????????????????? */
    protected ResponseEntity<Result<?>> handleMissingPathVariableException(MissingPathVariableException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.INTERNAL_SERVER_ERROR, ex, headers, request);
    }

    /** ?????????????????? */
    protected ResponseEntity<Result<?>> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.BAD_REQUEST, ex, headers, request);
    }

    /** ??????????????????????????????????????????????????????????????????????????? */
    protected ResponseEntity<Result<?>> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.BAD_REQUEST, ex, headers, request);
    }

    /** ???????????? bean ????????????????????????????????????????????????????????? */
    protected ResponseEntity<Result<?>> handleConversionNotSupportedException(ConversionNotSupportedException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.INTERNAL_SERVER_ERROR, ex, headers, request);
    }

    /** ???????????? bean ??????????????????????????????????????? */
    protected ResponseEntity<Result<?>> handleTypeMismatchException(TypeMismatchException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.BAD_REQUEST, ex, headers, request);
    }

    /** ???HttpMessageConverter.read??????????????????HttpMessageConverter??????????????? */
    protected ResponseEntity<Result<?>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.BAD_REQUEST, ex, headers, request);
    }

    /** ???HttpMessageConverter.write??????????????????HttpMessageConverter???????????? */
    protected ResponseEntity<Result<?>> handleHttpMessageNotWritableException(HttpMessageNotWritableException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.INTERNAL_SERVER_ERROR, ex, headers, request);
    }

    /** ???????????????????????????????????????multipart/form-data?????????????????????????????????<br/>???????????????????????????????????????/???????????????????????????????????????????????????????????????????????? Web ?????????????????????????????????????????????????????????????????????MultipartResolver */
    protected ResponseEntity<Result<?>> handleMissingServletRequestPartException(MissingServletRequestPartException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.BAD_REQUEST, ex, headers, request);
    }

    /** ?????????????????? */
    protected ResponseEntity<Result<?>> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.NOT_FOUND, ex, headers, request);
    }

    /** ???????????????????????????????????????<br/> ?????????????????????????????????DeferredResultProcessingInterceptor???CallableProcessingInterceptor????????? MVC Java ????????? MVC XML ???????????????????????????RequestMappingHandlerAdapter????????????????????? */
    @Nullable
    protected ResponseEntity<Result<?>> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, WebRequest webRequest) {
        return this.handleExceptionInternal(ResultStatusEnum.SERVICE_UNAVAILABLE, ex, headers, webRequest);
    }

    @Data
    @AllArgsConstructor
    private static class BindObjectError {
        private String defaultMessage;
        private String field;
        private String code;
    }
}
