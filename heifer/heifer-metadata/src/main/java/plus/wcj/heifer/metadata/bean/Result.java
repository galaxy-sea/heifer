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

package plus.wcj.heifer.metadata.bean;


import plus.wcj.heifer.metadata.exception.ResultStatus;
import plus.wcj.heifer.metadata.exception.ResultStatusEnum;

import lombok.Getter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

/**
 * 前后端和微服务的交互格式
 * {
 * "code": "",
 * "message": "",
 * "data": {}
 * }
 *
 * @author changjin wei(魏昌进)
 * @since 2021/4/23
 */
@Getter
@ToString
@SuppressWarnings("unused")
public final class Result<T> {

    public static final String RESULT_HEADER_KEY = "X-Result-Operation";

    public static final String RESULT_HEADER_VALUE = "yes";
    public static final List<String> RESULT_HEADER_VALUES = Collections.singletonList(Result.RESULT_HEADER_VALUE);

    /** 业务错误码 */
    private String code;
    /** 信息描述 */
    private String message;
    /** 返回参数 */
    private T data;

    private Result() {
    }

    public Result(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * @return new {@link Result}
     */
    public static Result<Void> success() {
        return Result.of(ResultStatusEnum.SUCCESS.getCode(), ResultStatusEnum.SUCCESS.getMessage(), null);
    }

    /**
     * 业务成功返回业务代码,描述和返回的参数
     *
     * @param data 返回的对象
     * @param <T> 返回的类型
     *
     * @return new {@link Result}
     */
    public static <T> Result<T> success(T data) {
        return Result.of(ResultStatusEnum.SUCCESS.getCode(), ResultStatusEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 业务异常返回业务代码,描述和返回的参数
     *
     * @param resultStatus 返回的状态 {@link ResultStatus}
     * @param <T> 返回的类型
     *
     * @return new {@link Result}
     */
    public static <T> Result<T> fail(ResultStatus resultStatus) {
        return Result.of(resultStatus.getCode(), resultStatus.getMessage(), null);
    }

    /**
     * 业务异常返回业务代码,描述和返回的参数
     *
     * @param resultStatus 返回的状态 {@link ResultStatus}
     * @param data 返回的对象
     * @param <T> 返回的类型
     *
     * @return new {@link Result}
     */
    public static <T> Result<T> fail(ResultStatus resultStatus, T data) {
        return Result.of(resultStatus.getCode(), resultStatus.getMessage(), data);
    }

    /**
     * 业务异常返回业务代码,描述和返回的参数
     *
     * @param code 返回的错误码
     * @param message 返回的消息
     * @param data 返回的对象
     * @param <T> 返回的类型
     *
     * @return new {@link Result}
     */
    public static <T> Result<T> of(String code, String message, T data) {
        return new Result<>(code, message, data);
    }
}