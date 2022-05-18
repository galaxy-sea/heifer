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

/**
 * @author changjin wei(魏昌进)
 * @since 2022/5/18
 */
public @interface CacheControl {

    long maxAge() default 0;

    boolean noCache() default false;

    boolean noStore() default false;

    boolean mustRevalidate() default false;

    boolean noTransform() default false;

    boolean cachePublic() default false;

    boolean cachePrivate() default false;

    boolean proxyRevalidate() default false;

    long staleWhileRevalidate() default 0;

    long staleIfError() default 0;

    long sMaxAge() default 0;
}
