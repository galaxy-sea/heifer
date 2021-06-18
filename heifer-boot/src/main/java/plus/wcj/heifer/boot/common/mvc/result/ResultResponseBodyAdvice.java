package plus.wcj.heifer.boot.common.mvc.result;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
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
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.util.WebUtils;
import plus.wcj.heifer.boot.common.exception.ResultException;
import plus.wcj.heifer.boot.common.exception.ResultStatus;

/**
 * @author changjin wei(魏昌进)
 * @date 2021/4/23
 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurer#configureMessageConverters(java.util.List)
 */
@RestControllerAdvice
@ControllerAdvice
@Component
@Slf4j
public class ResultResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    /** 判断类或者方法是否使用了 @ResponseResultBody */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ResultResponseBody.class) || returnType.hasMethodAnnotation(ResultResponseBody.class);
    }

    /** 当类或者方法使用了 @ResponseResultBody 就会调用这个方法 */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof Result) {
            return body;
        }
        return Result.success(body);
    }


    /**
     * 提供对标准Spring MVC异常的处理
     *
     * @param ex the target exception
     * @param request the current request
     */
    @ExceptionHandler({

            Exception.class,

            // 自定义异常
            ResultException.class,

            // 已知异常  虽然在这里重新添加一遍很傻逼，但是也许有个小可爱在if写了一样的代码呐
            BindException.class,
            BadCredentialsException.class,
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
        log.error("{}: {}", ex.getClass(), ex.getMessage());
        if (log.isDebugEnabled()) {
            log.debug("小可爱，注意检查代码哦", ex);
        }
        ex.printStackTrace();
        HttpHeaders headers = new HttpHeaders();
        if (ex instanceof ResultException) {
            return this.handleResultException((ResultException) ex, headers, request);
        }
        if (ex instanceof MethodArgumentNotValidException) {
            return handleMethodArgumentNotValidException((MethodArgumentNotValidException) ex, headers, request);
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
            return handleHttpRequestMethodNotSupportedException((HttpRequestMethodNotSupportedException) ex, headers, request);
        }
        if (ex instanceof HttpMediaTypeNotSupportedException) {
            return handleHttpMediaTypeNotSupportedException((HttpMediaTypeNotSupportedException) ex, headers, request);
        }
        if (ex instanceof HttpMediaTypeNotAcceptableException) {
            return handleHttpMediaTypeNotAcceptableException((HttpMediaTypeNotAcceptableException) ex, headers, request);
        }
        if (ex instanceof MissingPathVariableException) {
            return handleMissingPathVariableException((MissingPathVariableException) ex, headers, request);
        }
        if (ex instanceof MissingServletRequestParameterException) {
            return handleMissingServletRequestParameterException((MissingServletRequestParameterException) ex, headers, request);
        }
        if (ex instanceof ServletRequestBindingException) {
            return handleServletRequestBindingException((ServletRequestBindingException) ex, headers, request);
        }
        if (ex instanceof ConversionNotSupportedException) {
            return handleConversionNotSupportedException((ConversionNotSupportedException) ex, headers, request);
        }
        if (ex instanceof TypeMismatchException) {
            return handleTypeMismatchException((TypeMismatchException) ex, headers, request);
        }
        if (ex instanceof HttpMessageNotReadableException) {
            return handleHttpMessageNotReadableException((HttpMessageNotReadableException) ex, headers, request);
        }
        if (ex instanceof HttpMessageNotWritableException) {
            return handleHttpMessageNotWritableException((HttpMessageNotWritableException) ex, headers, request);
        }
        if (ex instanceof MissingServletRequestPartException) {
            return handleMissingServletRequestPartException((MissingServletRequestPartException) ex, headers, request);
        }
        if (ex instanceof NoHandlerFoundException) {
            return handleNoHandlerFoundException((NoHandlerFoundException) ex, headers, request);
        }
        if (ex instanceof AsyncRequestTimeoutException) {
            return handleAsyncRequestTimeoutException((AsyncRequestTimeoutException) ex, headers, request);
        }

        // TODO: 2019/10/05 galaxy 这里可以自定义其他的异常拦截
        return this.handleException(ex, headers, request);
    }

    private ResponseEntity<Result<?>> handleAccessDeniedException(AccessDeniedException ex, HttpHeaders headers, WebRequest request) {
        Result<?> body = Result.fail(ResultStatus.FORBIDDEN);
        HttpStatus status = HttpStatus.FORBIDDEN;
        return this.handleExceptionInternal(ex, body, headers, status, request);
    }

    private ResponseEntity<Result<?>> handleBadCredentialsException(BadCredentialsException ex, HttpHeaders headers, WebRequest request) {
        Result<?> body = Result.fail(ResultStatus.INCORRECT_USERNAME_OR_PASSWORD);
        HttpStatus status = HttpStatus.OK;
        return this.handleExceptionInternal(ex, body, headers, status, request);
    }


    /** 自定义异常处理 */
    protected ResponseEntity<Result<?>> handleResultException(ResultException ex, HttpHeaders headers, WebRequest request) {
        Result<?> body = Result.fail(ex.getResultStatus());
        HttpStatus status = ex.getResultStatus().getHttpStatus();
        return this.handleExceptionInternal(ex, body, headers, status, request);
    }

    /** validation校验 */
    protected ResponseEntity<Result<?>> handleBindException(BindException ex, HttpHeaders headers, WebRequest request) {
        ObjectError objectError = ex.getAllErrors().get(0);
        Result<?> body = Result.fail(ResultStatus.BAD_REQUEST, objectError.getDefaultMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return this.handleExceptionInternal(ex, body, headers, status, request);
    }


    protected ResponseEntity<Result<?>> handleException(Exception ex, HttpHeaders headers, WebRequest request) {
        Result<?> body = Result.fail(ResultStatus.INTERNAL_SERVER_ERROR);
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return this.handleExceptionInternal(ex, body, headers, status, request);
    }

    // TODO: 2021/6/17 changjin wei(魏昌进)
    protected ResponseEntity<Result<?>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, WebRequest request) {
        Result<?> body = Result.fail(ResultStatus.FORBIDDEN);
        HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
        return this.handleExceptionInternal(ex, body, headers, status, request);
    }

    protected ResponseEntity<Result<?>> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, WebRequest request) {

        HttpStatus status = HttpStatus.UNSUPPORTED_MEDIA_TYPE;

        return handleExceptionInternal(ex, null, headers, status, request);
    }

    protected ResponseEntity<Result<?>> handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_ACCEPTABLE;
        return handleExceptionInternal(ex, null, headers, status, request);
    }

    protected ResponseEntity<Result<?>> handleMissingPathVariableException(MissingPathVariableException ex, HttpHeaders headers, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(ex, null, headers, status, request);
    }

    protected ResponseEntity<Result<?>> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex, HttpHeaders headers, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return handleExceptionInternal(ex, null, headers, status, request);
    }

    protected ResponseEntity<Result<?>> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return handleExceptionInternal(ex, null, headers, status, request);
    }

    protected ResponseEntity<Result<?>> handleConversionNotSupportedException(ConversionNotSupportedException ex, HttpHeaders headers, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(ex, null, headers, status, request);
    }

    protected ResponseEntity<Result<?>> handleTypeMismatchException(TypeMismatchException ex, HttpHeaders headers, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return handleExceptionInternal(ex, null, headers, status, request);
    }

    protected ResponseEntity<Result<?>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpHeaders headers, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return handleExceptionInternal(ex, null, headers, status, request);
    }

    protected ResponseEntity<Result<?>> handleHttpMessageNotWritableException(HttpMessageNotWritableException ex, HttpHeaders headers, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return handleExceptionInternal(ex, null, headers, status, request);
    }

    protected ResponseEntity<Result<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpHeaders headers, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return handleExceptionInternal(ex, null, headers, status, request);
    }

    protected ResponseEntity<Result<?>> handleMissingServletRequestPartException(MissingServletRequestPartException ex, HttpHeaders headers, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return handleExceptionInternal(ex, null, headers, status, request);
    }


    protected ResponseEntity<Result<?>> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return handleExceptionInternal(ex, null, headers, status, request);
    }

    @Nullable
    protected ResponseEntity<Result<?>> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, WebRequest webRequest) {

        HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
        return handleExceptionInternal(ex, null, headers, status, webRequest);
    }


    /**
     * org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleExceptionInternal(java.lang.Exception, java.lang.Object, org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus, org.springframework.web.context.request.WebRequest)
     * <p>
     * A single place to customize the response body of all exception types.
     * <p>The default implementation sets the {@link WebUtils#ERROR_EXCEPTION_ATTRIBUTE}
     * request attribute and creates a {@link ResponseEntity} from the given
     * body, headers, and status.
     */
    protected ResponseEntity<Result<?>> handleExceptionInternal(Exception ex, Result<?> body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        // if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
        //     request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        // }
        return new ResponseEntity<>(body, headers, status);
    }


}

