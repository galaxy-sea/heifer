/*
 * Copyright 2021-2023 the original author or authors.
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

package plus.wcj.heifer.plugin.iam.auth.permission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import plus.wcj.heifer.plugin.iam.dao.AuthDao;
import plus.wcj.heifer.plugin.iam.service.IamRoleService;

import java.util.List;
import java.util.Set;

/**
 * 获取功能权限
 * @author changjin wei(魏昌进)
 * @since 2023/4/5
 */
@Component
@RequiredArgsConstructor
public class RolePermissionCustomizer implements PermissionCustomizer{

    private final AuthDao authDao;

    public List<String> customize(Long accountId, Long tenantId) {
        return authDao.selectByIamIamAccountIdAndTenantId(accountId, tenantId);
    }

}
