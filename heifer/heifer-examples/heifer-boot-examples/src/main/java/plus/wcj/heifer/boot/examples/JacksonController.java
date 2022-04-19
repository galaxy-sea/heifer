package plus.wcj.heifer.boot.examples;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/19
 */
@RestController
@RequestMapping("jackson")
public class JacksonController {

    @GetMapping()
    public ValidatedDto jackson() {
        ValidatedDto validatedDto = new ValidatedDto();
        validatedDto.setName("name");
        validatedDto.setStatus(2);
        validatedDto.setAge(3L);
        validatedDto.setMoney(new BigDecimal("10"));
        validatedDto.setGradeEnum(GradeEnum.PRIMARY);
        return validatedDto;
    }

    @PostMapping()
    public ValidatedDto jackson(@RequestBody ValidatedDto validatedDto) {
        return validatedDto;
    }

    @GetMapping("string")
    public String string() {
        return "hello";
    }
}
