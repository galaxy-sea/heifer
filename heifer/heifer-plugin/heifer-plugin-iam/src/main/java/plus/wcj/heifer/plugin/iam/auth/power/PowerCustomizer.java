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

package plus.wcj.heifer.plugin.iam.auth.power;

import java.util.Set;

/**
 * 获取数据权限
 * @author changjin wei(魏昌进)
 * @since 2023/4/5
 */
public interface PowerCustomizer {

    /**
     * 自定义返回功能权限集合
     *
     * @return 功能权限
     */
    Set<String> customize();

}
