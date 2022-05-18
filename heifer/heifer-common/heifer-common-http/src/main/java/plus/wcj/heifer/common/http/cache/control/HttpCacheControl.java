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


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * mapping 缓存注解, 会在response上面加上 cache-control
 * <p>
 * <a hrefdefault"https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Cache-Control">http Cache-Control</a>
 *
 * @author changjin wei(魏昌进)
 * @since 2022/5/15
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HttpCacheControl {

    /**
     * eTag绑定一个唯一资源key,
     * <p>
     * 支持部分spel表达式,
     * <p>
     * see {@link HttpETag#key()}
     *
     * @return eTagKey
     */
    String key();

    String value() default "max-age=190501, public";

    CacheControl cacheControl() default @CacheControl;
}

