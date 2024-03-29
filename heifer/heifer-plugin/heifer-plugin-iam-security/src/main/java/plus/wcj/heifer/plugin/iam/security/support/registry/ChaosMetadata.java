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

package plus.wcj.heifer.plugin.iam.security.support.registry;

import plus.wcj.heifer.plugin.iam.security.util.ChaosUtils;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/9/18
 */
public class ChaosMetadata {

    private final String chaos = ChaosUtils.gen();

    public String getChaos() {
        return chaos;
    }
}
