package plus.wcj.heifer.boot.common.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * http异常
 * 1XX - 5XX 使用http状态码
 *
 * A-XXXX - Z-XXXX 代表业务异常状态嘛
 *
 * @author changjin wei(魏昌进)
 * @date 2021/4/23
 */
@ToString
@Getter
public enum ResultStatus {
    /** 请求成功 */
    SUCCESS(HttpStatus.OK, "200", "OK"),
    /** Bad Request , 请求缺少参数 */
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "400", "Bad Request"),
    /** 用户未登录 */
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "401", "Unauthorized"),
    /** 用户已登陆,但是无权访问该资源 */
    FORBIDDEN(HttpStatus.FORBIDDEN, "403", "Forbidden"),
    /** 服务器内部错误 */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500", "Internal Server Error"),
    ;

    /** 返回的HTTP状态码,  符合http请求 */
    private final HttpStatus httpStatus;
    /** 业务异常码 */
    private final String code;
    /** 业务异常信息描述 */
    private final String message;

    ResultStatus(HttpStatus httpStatus, String code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
