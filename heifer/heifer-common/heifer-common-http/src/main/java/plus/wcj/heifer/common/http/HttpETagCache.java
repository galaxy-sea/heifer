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


    private final Cache keyCache;
    private final Cache valueCache;

    private final Sequence sequence;
    private static final String KEY_NAME = "eTag:k";
    private static final String VALUE_NAME = "eTag:v";

    public HttpETagCache(CacheManager cacheManager, Sequence sequence) {
        this.sequence = sequence;
        this.keyCache = cacheManager.getCache(KEY_NAME);
        this.valueCache = cacheManager.getCache(VALUE_NAME);
    }

    /**
     * 双向维护 key和eTag的关系
     *
     * @param key 唯一资源绑定关系
     *
     * @return eTag
     */
    public String put(String key) {
        if (!StringUtils.hasText(key)) {
            return null;
        }
        String eTag = "\"" + sequence.nextId() + "\"";
        String oldETag = keyCache.get(key, String.class);
        if (StringUtils.hasText(oldETag)) {
            valueCache.evict(oldETag);
        }
        keyCache.put(key, eTag);
        valueCache.put(eTag, key);
        return eTag;
    }

    /**
     * 通过key获取eTag
     *
     * @param key 唯一资源绑定关系
     *
     * @return eTag
     */
    public String get(String key) {
        if (!StringUtils.hasText(key)) {
            return null;
        }
        return keyCache.get(key, String.class);
    }

    /**
     * 通过key获取eTag,
     * @param key
     * @return 存在则返回默认的eTag不存在则返回一个默认的
     */
    public String getOrDefault(String key) {
        if (!StringUtils.hasText(key)) {
            return null;
        }
        String eTag = keyCache.get(key, String.class);
        return StringUtils.hasText(eTag) ? eTag : put(key);
    }


    /**
     * 查看eTag存在嘛
     *
     * @param eTag eTag
     *
     * @return 存在这个eTag就返回对应的key
     */
    public boolean hasETag(String eTag) {
        if (!StringUtils.hasText(eTag)) {
            return false;
        }
        return Objects.nonNull(valueCache.get(eTag));
    }
}
