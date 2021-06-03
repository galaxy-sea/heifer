package plus.wcj.heifer.boot.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.common.validator.dto.PostValid;

import javax.validation.constraints.NotNull;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/6/2
 */
@RestController
@RequestMapping("test")
public class TestController {

    @PostMapping("1")
    @ResponseBody
    public Object get(@RequestBody @Validated(PostValid.class) Haha haha) {
        return haha;
    }
}

@Data
@NoArgsConstructor
class Haha {
    @NotNull(groups = PostValid.class)
    private String name;

    @NotNull(groups = PostValid.class)
    private String age;

}
