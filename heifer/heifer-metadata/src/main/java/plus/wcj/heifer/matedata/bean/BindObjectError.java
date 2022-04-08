package plus.wcj.heifer.matedata.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/7
 */
@Data
@AllArgsConstructor
public class BindObjectError {
    /**
     * 默认信息
     */
    private String defaultMessage;
    /**
     * 字段
     */
    private String field;
    /**
     * 错误代码
     */
    private String code;
}
