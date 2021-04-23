package puls.wcj.heifer.boot.common.exception;

import lombok.Getter;

/**
 * @author changjin wei(魏昌进)
 * @date 2021/4/23
 */
@Getter
public class ResultException extends Exception {

    /**
     * 业务异常信息信息
     */
    ResultStatus resultStatus;

    public ResultException() {
        this(ResultStatus.INTERNAL_SERVER_ERROR);
    }

    public ResultException(ResultStatus resultStatus) {
        super(resultStatus.getMessage());
        this.resultStatus = resultStatus;
    }
}
