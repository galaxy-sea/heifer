package plus.wcj.heifer.matedata.bean;


import plus.wcj.heifer.matedata.exception.ResultStatus;
import plus.wcj.heifer.matedata.exception.ResultStatusEnum;

import lombok.Getter;
import lombok.ToString;

/**
 * @author changjin wei(魏昌进)
 * @date 2021/4/23
 */
@Getter
@ToString
@SuppressWarnings("unused")
public final class Result<T> {
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

    /** 业务成功返回业务代码和描述信息 */
    public static Result<Void> success() {
        return Result.of(ResultStatusEnum.SUCCESS.getCode(), ResultStatusEnum.SUCCESS.getMessage(), null);
    }

    /** 业务成功返回业务代码,描述和返回的参数 */
    public static <T> Result<T> success(T data) {
        return Result.of(ResultStatusEnum.SUCCESS.getCode(), ResultStatusEnum.SUCCESS.getMessage(), data);
    }

    /** 业务异常返回业务代码,描述和返回的参数 */
    public static <T> Result<T> fail(ResultStatus resultStatus) {
        return Result.of(resultStatus.getCode(), resultStatus.getMessage(), null);
    }

    public static <T> Result<T> fail(ResultStatus resultStatus, T data) {
        return Result.of(resultStatus.getCode(), resultStatus.getMessage(), data);
    }

    /** 业务异常返回业务代码,描述和返回的参数 */
    public static <T> Result<T> of(String code, String message, T data) {
        return new Result<>(code, message, data);
    }
}