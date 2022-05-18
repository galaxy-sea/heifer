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

import plus.wcj.heifer.common.http.sequence.Sequence;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 主要用于缓存
 *
 * @author changjin wei(魏昌进)
 * @since 2022/5/16
 */
public class HttpETagCache {

    private final CacheManager cacheManager;
    private final Sequence sequence;
    public static final String KEY_NAME = "eTag";

    public HttpETagCache(CacheManager cacheManager, Sequence sequence) {
        this.cacheManager = cacheManager;
        this.sequence = sequence;
    }

    /**
     * 双向维护 key和eTag的关系
     * @param key 唯一资源绑定关系
     * @return eTag
     */
    public String put(String key) {
        if (!StringUtils.hasText(key)) {
            return null;
        }
        String eTag = "\"" + sequence.nextId() + "\"";
        Cache cache = cacheManager.getCache(KEY_NAME);
        cache.put(key, eTag);
        cache.put(eTag, key);
        return eTag;
    }

    /**
     * 通过key获取eTag
     * @param key 唯一资源绑定关系
     * @return eTag
     */
    public String get(String key) {
        if (!StringUtils.hasText(key)) {
            return null;
        }
        Cache cache = cacheManager.getCache(KEY_NAME);
        return cache.get(key, String.class);
    }


    /**
     *  查看eTag存在嘛
     * @param eTag eTag
     * @return 存在这个eTag就返回对应的key
     */
    public boolean hasETag(String eTag) {
        if (!StringUtils.hasText(eTag)) {
            return false;
        }
        Cache cache = cacheManager.getCache(KEY_NAME);
        return Objects.nonNull(cache.get(eTag));
    }
}
