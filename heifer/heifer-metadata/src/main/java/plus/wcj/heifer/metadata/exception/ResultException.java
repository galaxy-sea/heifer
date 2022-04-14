package plus.wcj.heifer.metadata.exception;

import lombok.Getter;

/**
 * @author changjin wei(魏昌进)
 * @date 2021/4/23
 */
@Getter
public class ResultException extends RuntimeException {
    /**
     * 业务异常信息信息
     */
    private final ResultStatus resultStatus;
    private final String originalMessage;
    private final Object[] ages;

    public ResultException() {
        this(ResultStatusEnum.INTERNAL_SERVER_ERROR);
    }

    public ResultException(ResultStatus resultStatus) {
        this(resultStatus, null);
    }

    public ResultException(ResultStatus resultStatus, String originalMessage, String... ages) {
        this.resultStatus = resultStatus;
        this.originalMessage = originalMessage;
        this.ages = ages;
    }
}
