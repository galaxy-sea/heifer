package plus.wcj.heifer.matedata.extension.exception;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/11/9
 */
public interface ResultStatus {
    int getHttpStatus();

    String getCode();

    String getMessage();

    String name();

}
