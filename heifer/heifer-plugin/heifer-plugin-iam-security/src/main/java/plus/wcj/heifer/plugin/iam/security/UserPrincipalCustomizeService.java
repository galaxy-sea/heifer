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

package plus.wcj.heifer.plugin.iam.security;

/**
 * 为了满足ABAC模型 让每一个微服务都可以自定义自己的功能权限
 *
 * @author changjin wei(魏昌进)
 * @since 2022/6/10
 */
public interface UserPrincipalCustomizeService {

    /**
     * 自定义UserPrincipal的数据
     *
     * @param userPrincipal 用户信息
     */
    void customizeUserPrincipal(UserPrincipal userPrincipal);

}
