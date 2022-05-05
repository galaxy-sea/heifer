package plus.wcj.heifer.metadata.exception;

import lombok.Getter;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/4/23
 */
@Getter
public class ResultException extends RuntimeException {
    /**
     * 业务异常信息信息
     */
    private final ResultStatus resultStatus;
    /** 异常信息占位符 */
    private final Object[] ages;

    public ResultException() {
        this(ResultStatusEnum.INTERNAL_SERVER_ERROR);
    }

    public ResultException(ResultStatus resultStatus, String... ages) {
        this.resultStatus = resultStatus;
        this.ages = ages;
    }
}
