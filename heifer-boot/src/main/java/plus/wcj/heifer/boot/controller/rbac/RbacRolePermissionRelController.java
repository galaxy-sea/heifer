package plus.wcj.heifer.boot.controller.rbac;

import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.entity.rbac.RbacRolePermissionRelDo;
import plus.wcj.heifer.boot.service.rbac.RbacRolePermissionRelService;
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
 * 角色功能权限关系表 前端控制器
 * </p>
 *
 * @author changjinwei
 * @since 2021-06-06
 */
@RestController
@RequestMapping("/rbac-role-permission-rel-do")
@RequiredArgsConstructor
public class RbacRolePermissionRelController {
    private final RbacRolePermissionRelService rbacRolePermissionRelService;

    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacRolePermissionRelDo getById(Long id) {
        return rbacRolePermissionRelService.getById(id);
    }

    @GetMapping
    @ResultResponseBody
    public Page<RbacRolePermissionRelDo> page(Page<RbacRolePermissionRelDo> page, RbacRolePermissionRelDo rbacRolePermissionRelDo) {
        return rbacRolePermissionRelService.page(page, rbacRolePermissionRelDo);
    }

    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) RbacRolePermissionRelDo rbacRolePermissionRelDo) {
        return rbacRolePermissionRelService.save(rbacRolePermissionRelDo);
    }

    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) RbacRolePermissionRelDo rbacRolePermissionRelDo) {
        return rbacRolePermissionRelService.updateById(rbacRolePermissionRelDo);
    }

    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(Long id) {
        return rbacRolePermissionRelService.removeById(id);
    }
}
