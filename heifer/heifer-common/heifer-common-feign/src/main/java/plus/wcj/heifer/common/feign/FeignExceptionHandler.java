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

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.nio.ByteBuffer;
import java.util.Optional;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/7
 */
@Slf4j
@ControllerAdvice
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
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
        Optional<ByteBuffer> byteBuffer = ex.responseBody();
        int status = ex.status();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(byteBuffer, headers, status);
    }

}
