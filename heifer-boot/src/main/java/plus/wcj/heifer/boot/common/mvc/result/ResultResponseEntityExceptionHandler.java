package plus.wcj.heifer.boot.common.mvc.result;

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
import plus.wcj.heifer.boot.common.exception.ResultException;
import plus.wcj.heifer.boot.common.exception.ResultStatus;
import plus.wcj.heifer.boot.common.exception.ResultStatusEnum;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/7/8
 */
@Slf4j
@ControllerAdvice
@RestControllerAdvice
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ResultResponseEntityExceptionHandler {

    private final MessageSource messageSource;


    /** 自定义异常处理 */
    protected ResponseEntity<Result<?>> handleResultException(ResultException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ex.getResultStatusEnum(), null, ex, headers, request);
    }

    @SuppressWarnings("unused")
    protected <T> ResponseEntity<Result<?>> handleExceptionInternal(ResultStatus resultStatus, T data, Exception ex, HttpHeaders headers, WebRequest request) {


        String message = messageSource.getMessage(resultStatus.getClass().getName() + '.' + resultStatus.name(), null, resultStatus.getMessage(), request.getLocale());
        Result<T> body = Result.of(resultStatus.getCode(), message, data);
        log.error("{}: {}", ex.getClass(), ex.getMessage());
        if (log.isDebugEnabled()) {
            log.debug("小可爱，注意检查代码哦", ex);
        }
        return new ResponseEntity<>(body, headers, resultStatus.getHttpStatus());
    }

    @SuppressWarnings("unused")
    protected <T> ResponseEntity<Result<?>> handleExceptionInternal(ResultStatus resultStatus, Exception ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(resultStatus, null, ex, headers, request);
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
            // validation
            BindException.class,
            // security
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
        HttpHeaders headers = new HttpHeaders();
        if (ex instanceof ResultException) {
            return this.handleResultException((ResultException) ex, headers, request);
        }
        if (ex instanceof MethodArgumentNotValidException) {
            return this.handleMethodArgumentNotValidException((MethodArgumentNotValidException) ex, headers, request);
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

        // TODO: 2019/10/05 galaxy 这里可以自定义其他的异常拦截
        return this.handleException(ex, headers, request);
    }

    private ResponseEntity<Result<?>> handleAccessDeniedException(AccessDeniedException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.FORBIDDEN, ex, headers, request);
    }

    private ResponseEntity<Result<?>> handleBadCredentialsException(BadCredentialsException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.INCORRECT_USERNAME_OR_PASSWORD, ex, headers, request);
    }


    /** validation校验 */
    protected ResponseEntity<Result<?>> handleBindException(BindException ex, HttpHeaders headers, WebRequest request) {
        ObjectError objectError = ex.getAllErrors().get(0);
        return this.handleExceptionInternal(ResultStatusEnum.BAD_REQUEST, objectError, ex, headers, request);
    }

    /** 所有未知异常 */
    protected ResponseEntity<Result<?>> handleException(Exception ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.INTERNAL_SERVER_ERROR, ex, headers, request);
    }

    /** 当请求处理程序不支持特定的请求方法时抛出异常 */
    protected ResponseEntity<Result<?>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.METHOD_NOT_ALLOWED, ex, headers, request);
    }

    /** 当客户端 POST、PUT 或 PATCH 请求处理程序不支持的类型的内容时引发异常。 */
    protected ResponseEntity<Result<?>> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.UNSUPPORTED_MEDIA_TYPE, ex, headers, request);
    }

    /** 当请求处理程序无法生成客户端可接受的响应时抛出异常。 */
    protected ResponseEntity<Result<?>> handleHttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.NOT_ACCEPTABLE, ex, headers, request);
    }

    /** 指示从 URL 提取的 URI 变量中不存在@RequestMapping方法的方法参数中预期的路径变量。 通常，这意味着 URI 模板与方法参数上声明的路径变量名称不匹配 */
    protected ResponseEntity<Result<?>> handleMissingPathVariableException(MissingPathVariableException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.INTERNAL_SERVER_ERROR, ex, headers, request);
    }

    /** 表示缺少参数 */
    protected ResponseEntity<Result<?>> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.BAD_REQUEST, ex, headers, request);
    }

    /** 致命绑定异常，当我们想将绑定异常视为不可恢复时抛出 */
    protected ResponseEntity<Result<?>> handleServletRequestBindingException(ServletRequestBindingException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.BAD_REQUEST, ex, headers, request);
    }

    /** 当无法为 bean 属性找到合适的编辑器或转换器时抛出异常 */
    protected ResponseEntity<Result<?>> handleConversionNotSupportedException(ConversionNotSupportedException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.INTERNAL_SERVER_ERROR, ex, headers, request);
    }

    /** 尝试设置 bean 属性时因类型不匹配引发异常 */
    protected ResponseEntity<Result<?>> handleTypeMismatchException(TypeMismatchException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.BAD_REQUEST, ex, headers, request);
    }

    /** 当HttpMessageConverter.read方法失败时由HttpMessageConverter实现抛出。 */
    protected ResponseEntity<Result<?>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.BAD_REQUEST, ex, headers, request);
    }

    /** 当HttpMessageConverter.write方法失败时由HttpMessageConverter实现抛出 */
    protected ResponseEntity<Result<?>> handleHttpMessageNotWritableException(HttpMessageNotWritableException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.INTERNAL_SERVER_ERROR, ex, headers, request);
    }

    /** 当对用@Valid注释的参数的验证失败时抛出的异常。 从 5.3 开始扩展BindException */
    protected ResponseEntity<Result<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpHeaders headers, WebRequest request) {
        ObjectError objectError = ex.getAllErrors().get(0);
        return this.handleExceptionInternal(ResultStatusEnum.BAD_REQUEST, objectError, ex, headers, request);
    }

    /** 当无法找到由其名称标识的“multipart/form-data”请求的一部分时引发。<br/>这可能是因为请求不是多部分/表单数据请求，因为该部分不存在于请求中，或者因为 Web 应用程序没有正确配置来处理多部分请求，例如没有MultipartResolver */
    protected ResponseEntity<Result<?>> handleMissingServletRequestPartException(MissingServletRequestPartException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.BAD_REQUEST, ex, headers, request);
    }

    /** 没有这个方法 */
    protected ResponseEntity<Result<?>> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, WebRequest request) {
        return this.handleExceptionInternal(ResultStatusEnum.NOT_FOUND, ex, headers, request);
    }

    /** 异步请求超时时抛出的异常。<br/> 或者，应用程序可以注册DeferredResultProcessingInterceptor或CallableProcessingInterceptor以通过 MVC Java 配置或 MVC XML 命名空间或直接通过RequestMappingHandlerAdapter属性来处理超时 */
    @Nullable
    protected ResponseEntity<Result<?>> handleAsyncRequestTimeoutException(AsyncRequestTimeoutException ex, HttpHeaders headers, WebRequest webRequest) {
        return this.handleExceptionInternal(ResultStatusEnum.SERVICE_UNAVAILABLE, ex, headers, webRequest);
    }
}
