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

package plus.wcj.heifer.plugin.saas.differentiation.service;

import plus.wcj.heifer.metadata.exception.ResultException;
import plus.wcj.heifer.plugin.iam.security.UserPrincipal;
import plus.wcj.heifer.plugin.saas.differentiation.SaaSResultStatus;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/6/12
 */
public final class SaaSProvider<T> {

    private Map<String, T> saasServiceMap;
    private T coreService;

    public SaaSProvider(ObjectProvider<List<T>> objectProvider) {
        List<T> saasServiceList = objectProvider.getIfAvailable(() -> {
            throw new ResultException(SaaSResultStatus.NO_IMPLEMENTATION_CLASS);
        });

        this.saasServiceMap = new HashMap<>();
        for (T service : saasServiceList) {
            SaaSService annotation = AnnotatedElementUtils.getMergedAnnotation(service.getClass(), SaaSService.class);
            if (annotation != null) {
                String permission = annotation.permission();
                this.saasServiceMap.put(permission, service);
            } else {
                this.coreService = service;
            }
        }
    }


    public T get() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Collection<? extends GrantedAuthority> authorities = userPrincipal.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            String permission = authority.getAuthority();
            T saasService = saasServiceMap.get(permission);
            if (saasService != null) {
                return saasService;
            }
        }
        return coreService;
    }
}
