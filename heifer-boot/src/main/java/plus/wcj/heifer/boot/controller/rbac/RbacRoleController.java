package plus.wcj.heifer.boot.controller.rbac;

import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.entity.rbac.RbacRoleDo;
import plus.wcj.heifer.boot.service.rbac.RbacRoleService;
import plus.wcj.heifer.boot.extension.validator.PostValid;
import plus.wcj.heifer.boot.extension.validator.PutValid;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.annotation.Validated;
import plus.wcj.heifer.boot.common.mvc.result.ResultResponseBody;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author changjinwei
 * @since 2021-06-23
 */
@RestController
@RequestMapping("/rbac-role-do")
@RequiredArgsConstructor
public class RbacRoleController {
    private final RbacRoleService rbacRoleService;

    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacRoleDo getById(Long id) {
        return rbacRoleService.getById(id);
    }

    @GetMapping
    @ResultResponseBody
    public Page<RbacRoleDo> page(Page<RbacRoleDo> page, RbacRoleDo rbacRoleDo) {
        return rbacRoleService.page(page, rbacRoleDo);
    }

    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) RbacRoleDo rbacRoleDo) {
        return rbacRoleService.save(rbacRoleDo);
    }

    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) RbacRoleDo rbacRoleDo) {
        return rbacRoleService.updateById(rbacRoleDo);
    }

    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(Long id) {
        return rbacRoleService.removeById(id);
    }
}
