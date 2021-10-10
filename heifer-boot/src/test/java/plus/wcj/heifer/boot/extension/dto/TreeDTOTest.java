package plus.wcj.heifer.boot.extension.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import plus.wcj.heifer.boot.entity.rbac.org.RbacDept;
import plus.wcj.heifer.boot.utils.TreeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/7/15
 */
class TreeDTOTest {


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

        System.out.println(new ObjectMapper().writeValueAsString(TreeUtils.terr(depts)));
        System.out.println(new ObjectMapper().writeValueAsString(TreeUtils.zTerr(depts)));

    }
}