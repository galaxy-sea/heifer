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
    final ResultStatusEnum resultStatusEnum;

    @SuppressWarnings("unused")
    public ResultException() {
        this(ResultStatusEnum.INTERNAL_SERVER_ERROR);
    }

    public ResultException(ResultStatusEnum resultStatusEnum) {
        super(resultStatusEnum.getMessage());
        this.resultStatusEnum = resultStatusEnum;
    }

    public ResultException(ResultStatusEnum resultStatusEnum, String originalMessage) {
        super(resultStatusEnum.getMessage() + "\n originalMessage" + originalMessage);
        this.resultStatusEnum = resultStatusEnum;
    }
}
