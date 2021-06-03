package plus.wcj.heifer.boot.common.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * @author changjin wei(魏昌进)
 * @date 2021/4/23
 */
@ToString
@Getter
public enum ResultStatus {
    /** 请求成功 */
    SUCCESS(HttpStatus.OK, 200, "OK"),
    /** Bad Request , 请求缺少参数 */
    BAD_REQUEST(HttpStatus.BAD_REQUEST, 400, "Bad Request"),
    /** 服务器内部错误 */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "Internal Server Error"),
    ;

    /** 返回的HTTP状态码,  符合http请求 */
    private final HttpStatus httpStatus;
    /** 业务异常码 */
    private final Integer code;
    /** 业务异常信息描述 */
    private final String message;

    ResultStatus(HttpStatus httpStatus, Integer code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
