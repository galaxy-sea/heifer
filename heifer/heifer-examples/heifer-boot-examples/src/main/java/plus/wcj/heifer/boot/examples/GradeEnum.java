package plus.wcj.heifer.boot.examples;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/18
 */
@SuppressWarnings("AlibabaEnumConstantsMustHaveComment")
@Getter
@AllArgsConstructor
public enum GradeEnum {
    PRIMARY(1, "小学"),
    SECONDORY(2, "中学"),
    HIGH(3, "高中");

    private final int value;
    private final String desc;
}
