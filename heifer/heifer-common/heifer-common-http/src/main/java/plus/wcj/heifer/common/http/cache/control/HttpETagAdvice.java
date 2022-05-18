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
import plus.wcj.heifer.common.http.Utils;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/5/15
 */
public class HttpETagAdvice implements AfterReturningAdvice {

    private final HttpETagCache httpETagCache;

    public HttpETagAdvice(HttpETagCache httpETagCache) {
        this.httpETagCache = httpETagCache;
    }

    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) {
        String spelExpression = method.getAnnotation(HttpETag.class).key();
        String key = Utils.parser(spelExpression, method, args);
        httpETagCache.put(key);
    }
}
