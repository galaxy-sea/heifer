package plus.wcj.heifer.boot.extension.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import plus.wcj.heifer.boot.entity.rbac.org.RbacDept;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/7/15
 */
@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class TreeDTOTest {

    private final ObjectMapper objectMapper;

    @Test
    public void sortTest() throws JsonProcessingException {
        List<RbacDept> depts = new ArrayList<RbacDept>() {
            private static final long serialVersionUID = -4453462510012035398L;

            {
                this.add(new RbacDept().setId(1L).setParentId(0L));
                this.add(new RbacDept().setId(2L).setParentId(1L));
                this.add(new RbacDept().setId(3L).setParentId(1L));
                this.add(new RbacDept().setId(4L).setParentId(2L));
                this.add(new RbacDept().setId(5L).setParentId(2L));
                this.add(new RbacDept().setId(6L).setParentId(10L));
            }
        };
        List<TreeDTO<RbacDept>> elements = depts.stream().map(user -> new TreeDTO<>(user.getId(), user.getParentId(), user)).collect(Collectors.toList());

        System.out.println(this.objectMapper.writeValueAsString(TreeDTO.terr(elements)));
        System.out.println(this.objectMapper.writeValueAsString(TreeDTO.zTerr(elements)));

    }
}