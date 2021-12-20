package plus.wcj.heifer.matedata.exception;

import org.springframework.http.HttpStatus;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/11/9
 */
public interface ResultStatus {
    HttpStatus getHttpStatus();

    String getCode();

    String getMessage();

    String name();

}
