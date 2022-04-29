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
