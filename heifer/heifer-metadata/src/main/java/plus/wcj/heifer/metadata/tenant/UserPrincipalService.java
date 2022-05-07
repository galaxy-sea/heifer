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

package plus.wcj.heifer.metadata.tenant;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @see <a href="https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/#spring-cloud-feign-inheritance">feign 继承支持<a/>
 * @since 2022-01-13
 */
public interface UserPrincipalService {
    /**
     * 尝试从缓存中获取功能权限，如果没有缓存中有就返回，缓存没有就从数据库中获取
     *
     * @param accountId 账号id
     * @param tenantId 租户id
     *
     * @return
     */
    @GetMapping("/account/getAllPermission")
    @Cacheable(cacheNames = "permission", key = "#accountId+':'+#tenantId")
    List<String> listPermission(@RequestParam Long accountId, @RequestParam Long tenantId);

    /**
     * 尝试从缓存中获取数据权限，如果没有缓存中有就返回，缓存没有就从数据库中获取
     * @param accountId 账号id
     * @param tenantId 租户id
     *
     * @return
     */
    @GetMapping("/account/getAllPower")
    @Cacheable(cacheNames = "power", key = "#accountId+':'+#tenantId")
    DataPowersDto listPower(@RequestParam Long accountId, @RequestParam Long tenantId);
}
