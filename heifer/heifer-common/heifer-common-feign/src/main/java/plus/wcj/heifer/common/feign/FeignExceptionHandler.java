package plus.wcj.heifer.common.feign;

import feign.FeignException;
import plus.wcj.heifer.matedata.constant.OrderedConstant;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/7
 */
@Slf4j
@ControllerAdvice
@RestControllerAdvice
@Order(value = OrderedConstant.OPEN_FEIGN)
public class FeignExceptionHandler {

    /**
     * 提供对标准 Feign 异常的处理
     *
     * @param ex the target exception
     * @param request the current request
     *
     * @return ResponseEntity
     */
    @ExceptionHandler({
            FeignException.class
    })
    public final ResponseEntity<?> exceptionHandler(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        return this.handleFeignException((FeignException) ex, headers, request);
    }

    private ResponseEntity<?> handleFeignException(FeignException ex, HttpHeaders headers, @SuppressWarnings("unused") WebRequest request) {
        log.error("feign调用异常\n{}: {}", ex.getClass(), ex.getMessage());
        if (log.isDebugEnabled()) {
            log.debug("小可爱，feign调用异常哦", ex);
        }
        byte[] content = ex.content();
        int status = ex.status();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(content, headers, status);
    }

}
