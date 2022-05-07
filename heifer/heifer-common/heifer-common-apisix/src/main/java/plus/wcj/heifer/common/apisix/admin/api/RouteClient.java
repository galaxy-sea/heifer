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

package plus.wcj.heifer.common.apisix.admin.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

/**
 * <a href="https://apisix.apache.org/zh/docs/apisix/admin-api/#route">https://apisix.apache.org/zh/docs/apisix/admin-api/#route</a>
 *
 * @author changjin wei(魏昌进)
 * @since 2022/5/4
 */
@FeignClient(name = "routeClient", url = "${heifer.apisix.server-addr?heifer.apisix.server-addr:http://192.168.31.112:9080}", path = "${heifer.apisix.server-path?heifer.apisix.server-path:apisix/admin/}")
public interface RouteClient {
    /**
     * 获取资源
     *
     * @param id routes id
     * @param token X-API-KEY
     *
     * @return json string
     */
    @GetMapping("routes/{id}")
    ResponseEntity<String> route(@PathVariable("id") String id, @RequestHeader(Constants.TOKEN_KEY) String token);


    /**
     * 根据 id 创建资源
     *
     * @param id routes id
     * @param body service info
     * @param token X-API-KEY
     *
     * @return json string
     */
    @PutMapping("routes/{id}")
    ResponseEntity<String> route(@PathVariable("id") String id, @RequestBody Map<String, Object> body, @RequestHeader(Constants.TOKEN_KEY) String token);
}
