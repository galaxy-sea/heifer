package plus.wcj.heifer.boot.common.exception;

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
    ResultStatus resultStatus;

    @SuppressWarnings("unused")
    public ResultException() {
        this(ResultStatus.INTERNAL_SERVER_ERROR);
    }

    public ResultException(ResultStatus resultStatus) {
        super(resultStatus.getMessage());
        this.resultStatus = resultStatus;
    }

    public ResultException(ResultStatus resultStatus, String originalMessage) {
        super(resultStatus.getMessage() + "\n originalMessage" + originalMessage);
        this.resultStatus = resultStatus;
    }
}
