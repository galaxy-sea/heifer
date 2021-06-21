package plus.wcj.heifer.boot.common.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * http异常<br>
 * 1XX - 5XX 使用http状态码<br>
 * A-XXXX - Z-XXXX 代表业务异常状态嘛<br>
 *
 *
 * A-XXXX是框架引发异常
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
    /** 没有的方法 */
    NOT_FOUND(HttpStatus.NOT_FOUND, "404", "Not Found"),
    /** 不允许的方法 */
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "405", "Method Not Allowed"),
    /** 不接受请求 */
    NOT_ACCEPTABLE(HttpStatus.NOT_ACCEPTABLE, "406", "Not Acceptable"),
    /** 不受支持的媒体类型 */
    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "415", "Unsupported Media Type"),

    /** 服务器内部错误 */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500", "Internal Server Error"),
    /** 暂停服务 */
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "503", "Service Unavailable"),
    /** 账号密码错误 */
    INCORRECT_USERNAME_OR_PASSWORD(HttpStatus.OK, "security-0001", "Incorrect username or password"),
    /**过期的token */
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "security-0002", "Expired token"),
    /** 未启用的token */
    TOKEN_BEFORE_USE_TIME(HttpStatus.UNAUTHORIZED, "security-0003", "token before use time"),
    JOSE_Exception(HttpStatus.INTERNAL_SERVER_ERROR, "security-0004", "Javascript Object Signing and Encryption (JOSE) exception"),
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
