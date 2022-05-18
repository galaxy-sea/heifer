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

package plus.wcj.heifer.common.http.cache.control;


import plus.wcj.heifer.common.http.HttpETagCache;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * mapping 缓存注解, 会在response上面加上 cache-control
 * <p>
 * <a hrefdefault"https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Cache-Control">http Cache-Control</a>
 *
 * @author changjin wei(魏昌进)
 * @since 2022/5/15
 */

public class HttpIfNoneMatchFilter extends OncePerRequestFilter {

    private final HttpETagCache httpETagCache;

    public HttpIfNoneMatchFilter(HttpETagCache httpETagCache) {
        this.httpETagCache = httpETagCache;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String eTag = request.getHeader(HttpHeaders.IF_NONE_MATCH);
        if (StringUtils.hasText(eTag) && httpETagCache.hasETag(eTag)) {
            response.sendError(HttpStatus.NOT_MODIFIED.value());
            response.flushBuffer();
            return;
        }
        filterChain.doFilter(request, response);
    }
}

