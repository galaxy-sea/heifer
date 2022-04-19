package plus.wcj.heifer.common.mybatis.plus.example;

import lombok.Data;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/19
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private GradeEnum grade;
}
