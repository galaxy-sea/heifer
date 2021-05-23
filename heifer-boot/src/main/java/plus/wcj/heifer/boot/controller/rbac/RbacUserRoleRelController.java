package plus.wcj.heifer.boot.controller.rbac;

import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.entity.rbac.RbacUserRoleRelDo;
import plus.wcj.heifer.boot.service.rbac.RbacUserRoleRelService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import plus.wcj.heifer.boot.common.mvc.result.ResultResponseBody;
import lombok.RequiredArgsConstructor;
/**
 * <p>
 * 角色用户关系表 前端控制器
 * </p>
 *
 * @author changjinwei
 * @since 2021-05-23
 */
@RestController
@RequestMapping("/rbac-user-role-rel-do")
@RequiredArgsConstructor
public class RbacUserRoleRelController {
    private final RbacUserRoleRelService rbacUserRoleRelService;

    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacUserRoleRelDo getById(Long id) {
        return rbacUserRoleRelService.getById(id);
    }

    @GetMapping
    @ResultResponseBody
    public Page<RbacUserRoleRelDo> page(Page<RbacUserRoleRelDo> page, RbacUserRoleRelDo rbacUserRoleRelDo) {
        return rbacUserRoleRelService.page(page, new QueryWrapper<>(rbacUserRoleRelDo));
    }

    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody RbacUserRoleRelDo rbacUserRoleRelDo) {
        return rbacUserRoleRelService.save(rbacUserRoleRelDo);
    }

    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody RbacUserRoleRelDo rbacUserRoleRelDo) {
        return rbacUserRoleRelService.updateById(rbacUserRoleRelDo);
    }

    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(Long id) {
        return rbacUserRoleRelService.removeById(id);
    }
}
