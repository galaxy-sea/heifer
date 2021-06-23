package plus.wcj.heifer.boot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import plus.wcj.heifer.boot.HeiferBootApplication;
import plus.wcj.heifer.boot.service.rbac.RbacUserService;

@SpringBootTest(classes = HeiferBootApplication.class)
public class UserMapperTest {

    @Autowired
    private RbacUserService userService;


    // @Test
    // public void MybatisPlusTest() {
    //
    //     List<RbacUserDo> rbacUserDoList = new ArrayList() {{
    //         add(new RbacUserDo().setTenantOrgId(1L).setRbacDeptId(1L).setUsername(String.valueOf(Math.random() * 1000)).setPhone(String.valueOf(Math.random() * 1000)).setEmail(String.valueOf(Math.random() * 1000)).setPassword(String.valueOf(Math.random() * 1000)).setNickname(String.valueOf(Math.random() * 1000)).setAccountNonExpired(false).setAccountNonLocked(false).setCredentialsNonExpired(false).setEnabled(false));
    //         add(new RbacUserDo().setTenantOrgId(1L).setRbacDeptId(1L).setUsername(String.valueOf(Math.random() * 1000)).setPhone(String.valueOf(Math.random() * 1000)).setEmail(String.valueOf(Math.random() * 1000)).setPassword(String.valueOf(Math.random() * 1000)).setNickname(String.valueOf(Math.random() * 1000)).setAccountNonExpired(false).setAccountNonLocked(false).setCredentialsNonExpired(false).setEnabled(false));
    //         add(new RbacUserDo().setTenantOrgId(1L).setRbacDeptId(1L).setUsername(String.valueOf(Math.random() * 1000)).setPhone(String.valueOf(Math.random() * 1000)).setEmail(String.valueOf(Math.random() * 1000)).setPassword(String.valueOf(Math.random() * 1000)).setNickname(String.valueOf(Math.random() * 1000)).setAccountNonExpired(false).setAccountNonLocked(false).setCredentialsNonExpired(false).setEnabled(false));
    //         add(new RbacUserDo().setTenantOrgId(1L).setRbacDeptId(1L).setUsername(String.valueOf(Math.random() * 1000)).setPhone(String.valueOf(Math.random() * 1000)).setEmail(String.valueOf(Math.random() * 1000)).setPassword(String.valueOf(Math.random() * 1000)).setNickname(String.valueOf(Math.random() * 1000)).setAccountNonExpired(false).setAccountNonLocked(false).setCredentialsNonExpired(false).setEnabled(false));
    //         add(new RbacUserDo().setTenantOrgId(1L).setRbacDeptId(1L).setUsername(String.valueOf(Math.random() * 1000)).setPhone(String.valueOf(Math.random() * 1000)).setEmail(String.valueOf(Math.random() * 1000)).setPassword(String.valueOf(Math.random() * 1000)).setNickname(String.valueOf(Math.random() * 1000)).setAccountNonExpired(false).setAccountNonLocked(false).setCredentialsNonExpired(false).setEnabled(false));
    //     }};
    //     userService.saveBatch(rbacUserDoList);
    //
    // }

}