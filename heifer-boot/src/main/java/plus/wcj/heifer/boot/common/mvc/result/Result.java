package plus.wcj.heifer.boot.common.mvc.result;

import lombok.Getter;
import lombok.ToString;
import plus.wcj.heifer.boot.common.exception.ResultStatus;

/**
 * @author changjin wei(魏昌进)
 * @date 2021/4/23
 */
@Getter
@ToString
@SuppressWarnings("unused")
public class Result<T> {
    /** 业务错误码 */
    private final String code;
    /** 信息描述 */
    private final String message;
    /** 返回参数 */
    private final T data;

    private Result(ResultStatus resultStatus, T data) {
        this.code = resultStatus.getCode();
        this.message = resultStatus.getMessage();
        this.data = data;
    }

    /** 业务成功返回业务代码和描述信息 */
    public static Result<Void> success() {
        return new Result<>(ResultStatus.SUCCESS, null);
    }

    /** 业务成功返回业务代码,描述和返回的参数 */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultStatus.SUCCESS, data);
    }

    /** 业务成功返回业务代码,描述和返回的参数 */
    public static <T> Result<T> success(ResultStatus resultStatus, T data) {
        return new Result<>(resultStatus, data);
    }

    /** 业务异常返回业务代码,描述和返回的参数 */
    public static <T> Result<T> fail(ResultStatus resultStatus) {
        return new Result<>(resultStatus, null);
    }

    /** 业务异常返回业务代码,描述和返回的参数 */
    public static <T> Result<T> fail(ResultStatus resultStatus, T data) {
        return new Result<>(resultStatus, data);
    }
}