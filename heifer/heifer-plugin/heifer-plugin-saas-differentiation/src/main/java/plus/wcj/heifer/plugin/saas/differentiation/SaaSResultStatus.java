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

package plus.wcj.heifer.plugin.saas.differentiation;

import plus.wcj.heifer.metadata.exception.ResultStatus;

import org.springframework.http.HttpStatus;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/6/13
 */
@SuppressWarnings("AlibabaEnumConstantsMustHaveComment")
public enum SaaSResultStatus implements ResultStatus {

    NO_IMPLEMENTATION_CLASS(HttpStatus.INTERNAL_SERVER_ERROR, "saas-0001", "no implementation class"),
    NO_DEFAULT_IMPLEMENTATION_CLAZZ(HttpStatus.INTERNAL_SERVER_ERROR, "saas-0002", "no default implementation class"),
    ;



    /** 返回的HTTP状态码,  符合http请求 */
    private final HttpStatus httpStatus;
    /** 业务异常码 */
    private final String code;
    /** 业务异常信息描述 */
    private final String message;

    SaaSResultStatus(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
