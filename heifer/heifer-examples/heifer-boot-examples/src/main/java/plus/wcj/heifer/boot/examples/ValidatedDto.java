package plus.wcj.heifer.boot.examples;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/18
 */
@Data
public class ValidatedDto {
    @NotNull
    private String name;

    private Integer status;

    private Long age;

    private BigDecimal money;

    private String nullTest;

    private GradeEnum gradeEnum;
}
