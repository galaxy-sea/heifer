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

package plus.wcj.heifer.metadata.exception;

import org.springframework.http.HttpStatus;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/11/9
 */
public interface ResultStatus {
    /**
     * http 状态码
     *
     * @return HttpStatus
     */
    HttpStatus getHttpStatus();

    /**
     * 全局唯一错误码
     *
     * @return 错误码
     */
    String getCode();

    /**
     * 错误信息
     *
     * @return 错误信息
     */
    String getMessage();

    /**
     * 获取枚举类名称，用于国际化
     *
     * @return 枚举名称
     *
     * @see Enum#name
     */
    String name();

}
