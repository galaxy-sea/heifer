package plus.wcj.heifer.common.mybatis.plus.example;

import com.baomidou.mybatisplus.annotation.EnumValue;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/19
 */
@Getter
@AllArgsConstructor
public enum GradeEnum {

    PRIMARY(1, "小学"),
    SECONDORY(2, "中学"),
    HIGH(3, "高中");

    @EnumValue
    private final int code;
    private final String descp;
}
