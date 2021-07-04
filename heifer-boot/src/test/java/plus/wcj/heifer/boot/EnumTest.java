package plus.wcj.heifer.boot;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.junit.jupiter.api.Test;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/6/22
 */
public class EnumTest {


    @Test
    public void data() throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        User user = new User();
        user.setName("xiaowei");
        user.setGrade(GradeEnum.HIGH);
        String s = objectMapper.writeValueAsString(user);
        System.out.println(s);

        User user1 = objectMapper.readValue(s, User.class);

        System.out.println(user1);


    }


}

@Data
class User {
    /**
     * 名字
     * 数据库字段: name varchar(20)
     */
    private String name;


    /**
     * 年级，原生枚举（带{@link com.baomidou.mybatisplus.annotation.EnumValue}):
     * 数据库字段：grade INT(2)
     */
    private GradeEnum grade;
}

@Getter
@ToString
enum GradeEnum{

    // @JsonProperty("小学")
    PRIMARY(1, "小学"),
    // @JsonProperty("中学")
    SECONDORY(2, "中学"),
    // @JsonProperty("高中")
    HIGH(3, "高中");

    private final String descp;

    @EnumValue//标记数据库存的值是code
    // @JsonValue
    private final int code;

    GradeEnum(int code, String descp) {
        this.code = code;
        this.descp = descp;
    }



    public GradeEnum[] data(){
        GradeEnum[] values = GradeEnum.values();
        return values;
    }


}



