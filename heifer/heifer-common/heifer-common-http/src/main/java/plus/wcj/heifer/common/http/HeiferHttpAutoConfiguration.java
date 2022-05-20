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

package plus.wcj.heifer.common.http;


import plus.wcj.heifer.common.http.cache.control.HttpAnnotationPointcutAdvisor;
import plus.wcj.heifer.common.http.cache.control.HttpCacheControl;
import plus.wcj.heifer.common.http.cache.control.HttpCacheControlAdvice;
import plus.wcj.heifer.common.http.cache.control.HttpETag;
import plus.wcj.heifer.common.http.cache.control.HttpETagAdvice;
import plus.wcj.heifer.common.http.cache.control.HttpIfNoneMatchFilter;
import plus.wcj.heifer.common.http.proxy.cache.ApisixProxyCache;
import plus.wcj.heifer.common.http.sequence.Sequence;

import org.springframework.aop.Advisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/5/15
 */
public class HeiferHttpAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public Sequence sequence() {
        return new Sequence();
    }

    @Bean
    @ConditionalOnMissingBean(name = "cacheManager")
    public HttpETagCache httpETagCache(Sequence sequence) {
        return new HttpETagCache(new ConcurrentMapCacheManager(), sequence);
    }

    @Bean
    @ConditionalOnMissingBean
    public Advisor HttpETagPointcutAdvisor(HttpETagCache httpETagCache) {
        HttpETagAdvice interceptor = new HttpETagAdvice(httpETagCache);
        return new HttpAnnotationPointcutAdvisor(interceptor, HttpETag.class);
    }

    @Bean
    @ConditionalOnMissingBean
    public Advisor httpCacheControlPointcutAdvisor(HttpETagCache httpETagCache) {
        HttpCacheControlAdvice annotationAdvice = new HttpCacheControlAdvice(httpETagCache);
        return new HttpAnnotationPointcutAdvisor(annotationAdvice, HttpCacheControl.class);
    }

    @Bean
    @ConditionalOnMissingBean
    public HttpIfNoneMatchFilter httpIfNoneMatchFilter(HttpETagCache httpETagCache) {
        return new HttpIfNoneMatchFilter(httpETagCache);
    }

    public static class HaveCacheManagerAutoConfiguration {
        @Bean
        @ConditionalOnMissingBean
        @ConditionalOnBean(name = {"cacheManager"})
        public HttpETagCache httpETagCache(CacheManager cacheManager, Sequence sequence) {
            return new HttpETagCache(cacheManager, sequence);
        }
    }


    public static class  ApisixProxyCacheAutoConfiguration{
        @Bean
        @ConditionalOnMissingBean
        @ConditionalOnClass(name = "plus.wcj.heifer.common.apisix.ApisixCustomizer")
        public ApisixProxyCache apisixProxyCache() {
            return new ApisixProxyCache();
        }
    }

}
