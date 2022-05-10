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

package plus.wcj.heifer.common.apisix;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.util.Map;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/5/9
 */
@Data
public class Route {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String id;
    private Object uri;
    private Object uris;
    private Map<String, Object> plugins;
    private Object script;
    private Map<String, Object> upstream;
    @JsonProperty("upstream_id")
    private Object upstreamId;
    @JsonProperty("service_id")
    private Object serviceId;
    @JsonProperty("plugin_config_id")
    private Object pluginConfigId;
    private Object name;
    private Object desc;
    private Object host;
    private Object hosts;
    @JsonProperty("remote_addr")
    private Object remoteAddr;
    @JsonProperty("remote_addrs")
    private Object remoteAddrs;
    private Object methods;
    private Object priority;
    private Object vars;
    @JsonProperty("filter_func")
    private Object filterFunc;
    private Map<String, String> labels;
    private Object timeout;
    @JsonProperty("enable_websocket")
    private Object enableWebsocket;
    private Object status;
    @JsonProperty("create_time")
    private Object createTime;
    @JsonProperty("update_time")
    private Object updateTime;
}
