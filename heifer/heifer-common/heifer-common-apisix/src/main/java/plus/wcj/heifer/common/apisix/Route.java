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

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/5/9
 */
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

    public synchronized Map<String, Object> getPlugins() {
        if (plugins == null) {
            plugins = new LinkedHashMap<>();
        }
        return plugins;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getUri() {
        return uri;
    }

    public void setUri(Object uri) {
        this.uri = uri;
    }

    public Object getUris() {
        return uris;
    }

    public void setUris(Object uris) {
        this.uris = uris;
    }

    public void setPlugins(Map<String, Object> plugins) {
        this.plugins = plugins;
    }

    public Object getScript() {
        return script;
    }

    public void setScript(Object script) {
        this.script = script;
    }

    public Map<String, Object> getUpstream() {
        return upstream;
    }

    public void setUpstream(Map<String, Object> upstream) {
        this.upstream = upstream;
    }

    public Object getUpstreamId() {
        return upstreamId;
    }

    public void setUpstreamId(Object upstreamId) {
        this.upstreamId = upstreamId;
    }

    public Object getServiceId() {
        return serviceId;
    }

    public void setServiceId(Object serviceId) {
        this.serviceId = serviceId;
    }

    public Object getPluginConfigId() {
        return pluginConfigId;
    }

    public void setPluginConfigId(Object pluginConfigId) {
        this.pluginConfigId = pluginConfigId;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getDesc() {
        return desc;
    }

    public void setDesc(Object desc) {
        this.desc = desc;
    }

    public Object getHost() {
        return host;
    }

    public void setHost(Object host) {
        this.host = host;
    }

    public Object getHosts() {
        return hosts;
    }

    public void setHosts(Object hosts) {
        this.hosts = hosts;
    }

    public Object getRemoteAddr() {
        return remoteAddr;
    }

    public void setRemoteAddr(Object remoteAddr) {
        this.remoteAddr = remoteAddr;
    }

    public Object getRemoteAddrs() {
        return remoteAddrs;
    }

    public void setRemoteAddrs(Object remoteAddrs) {
        this.remoteAddrs = remoteAddrs;
    }

    public Object getMethods() {
        return methods;
    }

    public void setMethods(Object methods) {
        this.methods = methods;
    }

    public Object getPriority() {
        return priority;
    }

    public void setPriority(Object priority) {
        this.priority = priority;
    }

    public Object getVars() {
        return vars;
    }

    public void setVars(Object vars) {
        this.vars = vars;
    }

    public Object getFilterFunc() {
        return filterFunc;
    }

    public void setFilterFunc(Object filterFunc) {
        this.filterFunc = filterFunc;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public void setLabels(Map<String, String> labels) {
        this.labels = labels;
    }

    public Object getTimeout() {
        return timeout;
    }

    public void setTimeout(Object timeout) {
        this.timeout = timeout;
    }

    public Object getEnableWebsocket() {
        return enableWebsocket;
    }

    public void setEnableWebsocket(Object enableWebsocket) {
        this.enableWebsocket = enableWebsocket;
    }

    public Object getStatus() {
        return status;
    }

    public void setStatus(Object status) {
        this.status = status;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Object updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Route{" +
            "id='" + id + '\'' +
            ", uri=" + uri +
            ", uris=" + uris +
            ", plugins=" + plugins +
            ", script=" + script +
            ", upstream=" + upstream +
            ", upstreamId=" + upstreamId +
            ", serviceId=" + serviceId +
            ", pluginConfigId=" + pluginConfigId +
            ", name=" + name +
            ", desc=" + desc +
            ", host=" + host +
            ", hosts=" + hosts +
            ", remoteAddr=" + remoteAddr +
            ", remoteAddrs=" + remoteAddrs +
            ", methods=" + methods +
            ", priority=" + priority +
            ", vars=" + vars +
            ", filterFunc=" + filterFunc +
            ", labels=" + labels +
            ", timeout=" + timeout +
            ", enableWebsocket=" + enableWebsocket +
            ", status=" + status +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            '}';
    }
}
