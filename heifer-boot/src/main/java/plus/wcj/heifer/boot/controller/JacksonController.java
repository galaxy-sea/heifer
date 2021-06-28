package plus.wcj.heifer.boot.controller;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author changjin wei(魏昌进)
 */
@RestController
@RequestMapping("jackson")
public class JacksonController {

    @GetMapping
    public Object jackson() {
        return new Jack().setAge(1).setName("123").setStringList(new ArrayList<>());
    }

}


@Data
@Accessors(chain = true)
class Jack {
    private String name;
    private Integer age;
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private List<String> stringList;
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private Set<String> stringSet;

    private String name1;
    private Integer age1;
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private List<String> stringList1;
    @JsonSetter(nulls = Nulls.AS_EMPTY)
    private Set<String> stringSet1;
}


