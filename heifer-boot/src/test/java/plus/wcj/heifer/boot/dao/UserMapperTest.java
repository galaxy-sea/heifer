package plus.wcj.heifer.boot.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import plus.wcj.heifer.boot.HeiferBootApplication;
import plus.wcj.heifer.boot.entity.rbac.RbacUserDo;
import plus.wcj.heifer.boot.service.rbac.RbacUserService;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = HeiferBootApplication.class)
public class UserMapperTest {

    @Autowired
    private RbacUserService userService;


    @Test
    public void MybatisPlusTest() {

        List<RbacUserDo> rbacUserDoList = new ArrayList() {{
            add(new RbacUserDo().setTenantOrgId(1L).setRbacDeptId(1L).setUsername(String.valueOf(Math.random() * 1000)).setPhone(String.valueOf(Math.random() * 1000)).setEmail(String.valueOf(Math.random() * 1000)).setPassword(String.valueOf(Math.random() * 1000)).setNickname(String.valueOf(Math.random() * 1000)).setIsAccountNonExpired(0).setIsAccountNonLocked(0).setIsCredentialsNonExpired(0).setIsEnabled(0));
            add(new RbacUserDo().setTenantOrgId(1L).setRbacDeptId(1L).setUsername(String.valueOf(Math.random() * 1000)).setPhone(String.valueOf(Math.random() * 1000)).setEmail(String.valueOf(Math.random() * 1000)).setPassword(String.valueOf(Math.random() * 1000)).setNickname(String.valueOf(Math.random() * 1000)).setIsAccountNonExpired(0).setIsAccountNonLocked(0).setIsCredentialsNonExpired(0).setIsEnabled(0));
            add(new RbacUserDo().setTenantOrgId(1L).setRbacDeptId(1L).setUsername(String.valueOf(Math.random() * 1000)).setPhone(String.valueOf(Math.random() * 1000)).setEmail(String.valueOf(Math.random() * 1000)).setPassword(String.valueOf(Math.random() * 1000)).setNickname(String.valueOf(Math.random() * 1000)).setIsAccountNonExpired(0).setIsAccountNonLocked(0).setIsCredentialsNonExpired(0).setIsEnabled(0));
            add(new RbacUserDo().setTenantOrgId(1L).setRbacDeptId(1L).setUsername(String.valueOf(Math.random() * 1000)).setPhone(String.valueOf(Math.random() * 1000)).setEmail(String.valueOf(Math.random() * 1000)).setPassword(String.valueOf(Math.random() * 1000)).setNickname(String.valueOf(Math.random() * 1000)).setIsAccountNonExpired(0).setIsAccountNonLocked(0).setIsCredentialsNonExpired(0).setIsEnabled(0));
            add(new RbacUserDo().setTenantOrgId(1L).setRbacDeptId(1L).setUsername(String.valueOf(Math.random() * 1000)).setPhone(String.valueOf(Math.random() * 1000)).setEmail(String.valueOf(Math.random() * 1000)).setPassword(String.valueOf(Math.random() * 1000)).setNickname(String.valueOf(Math.random() * 1000)).setIsAccountNonExpired(0).setIsAccountNonLocked(0).setIsCredentialsNonExpired(0).setIsEnabled(0));
        }};
        userService.saveBatch(rbacUserDoList);

    }

}