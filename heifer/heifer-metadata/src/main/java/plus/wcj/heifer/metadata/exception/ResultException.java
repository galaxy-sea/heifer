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

import lombok.Getter;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/4/23
 */
@Getter
public class ResultException extends RuntimeException {
    /**
     * 业务异常信息信息
     */
    private final ResultStatus resultStatus;
    /** 异常信息占位符 */
    private final Object[] ages;

    public ResultException() {
        this(ResultStatusEnum.INTERNAL_SERVER_ERROR);
    }

    public ResultException(ResultStatus resultStatus, String... ages) {
        this.resultStatus = resultStatus;
        this.ages = ages;
    }
}
