package plus.wcj.heifer.boot.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.common.mvc.result.ResultResponseBody;
import plus.wcj.heifer.boot.common.validator.dto.PostValid;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

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

    @GetMapping("2")
    @ResponseBody
    @ResultResponseBody
    public Object get(Date date) {
        Haha haha = new Haha();
        haha.setDate(new Date());
        haha.setDate2(date);
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

    private Date date;
    private Date date2;

    private long date1 = 123123123123123L;

    private List list;

}
