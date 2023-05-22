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

package plus.wcj.heifer.common.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>
 * 忽略配置
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2022-01-13
 */
@ConfigurationProperties(prefix = "heifer.security.ignore.matchers")
public class IgnoreWebSecurityProperties {
    /**
     * 需要忽略的 URL 格式，不考虑请求方法
     */
    private String[] pattern = {};

    /**
     * 需要忽略的 GET 请求
     */
    private String[] get = {};

    /**
     * 需要忽略的 POST 请求
     */
    private String[] post = {};

    /**
     * 需要忽略的 DELETE 请求
     */
    private String[] delete = {};

    /**
     * 需要忽略的 PUT 请求
     */
    private String[] put = {};

    /**
     * 需要忽略的 HEAD 请求
     */
    private String[] head = {};

    /**
     * 需要忽略的 PATCH 请求
     */
    private String[] patch = {};

    /**
     * 需要忽略的 OPTIONS 请求
     */
    private String[] options = {};

    /**
     * 需要忽略的 TRACE 请求
     */
    private String[] trace = {};

    public String[] getPattern() {
        return pattern;
    }

    public void setPattern(String[] pattern) {
        this.pattern = pattern;
    }

    public String[] getGet() {
        return get;
    }

    public void setGet(String[] get) {
        this.get = get;
    }

    public String[] getPost() {
        return post;
    }

    public void setPost(String[] post) {
        this.post = post;
    }

    public String[] getDelete() {
        return delete;
    }

    public void setDelete(String[] delete) {
        this.delete = delete;
    }

    public String[] getPut() {
        return put;
    }

    public void setPut(String[] put) {
        this.put = put;
    }

    public String[] getHead() {
        return head;
    }

    public void setHead(String[] head) {
        this.head = head;
    }

    public String[] getPatch() {
        return patch;
    }

    public void setPatch(String[] patch) {
        this.patch = patch;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public String[] getTrace() {
        return trace;
    }

    public void setTrace(String[] trace) {
        this.trace = trace;
    }
}
