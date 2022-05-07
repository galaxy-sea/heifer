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

package plus.wcj.heifer.metadata.properties;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;

/**
 * <p>
 * JWT 配置
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2022-01-13
 */
@Data
public class JwtProperties {
    /**
     * jwt 加密 key，默认值：xxxxxxxxxxxxxxx.
     */
    @Value(value = "${heifer.jwt.key?heifer.jwt.key:xxxxxxxxxxxxxxx}")
    private String key;

    /**
     * jwt 过期时间，默认值：600000 {@code 10 分钟}.
     */
    @Value(value = "${heifer.jwt.ttl?heifer.jwt.ttl:600000}")
    private Long ttl;

    /**
     * 开启 记住我 之后 jwt 过期时间，默认值 604800000 {@code 7 天}
     */
    @Value(value = "${heifer.jwt.remember?heifer.jwt.remember:604800000}")
    private Long remember;
}
