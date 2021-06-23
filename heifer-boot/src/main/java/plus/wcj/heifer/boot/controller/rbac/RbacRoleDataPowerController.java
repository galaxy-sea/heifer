package plus.wcj.heifer.boot.controller.rbac;

import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.entity.rbac.RbacRoleDataPowerDo;
import plus.wcj.heifer.boot.service.rbac.RbacRoleDataPowerService;
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
 * 角色部门权限 前端控制器
 * </p>
 *
 * @author changjinwei
 * @since 2021-06-23
 */
@RestController
@RequestMapping("/rbac-role-data-power-do")
@RequiredArgsConstructor
public class RbacRoleDataPowerController {
    private final RbacRoleDataPowerService rbacRoleDataPowerService;

    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacRoleDataPowerDo getById(Long id) {
        return rbacRoleDataPowerService.getById(id);
    }

    @GetMapping
    @ResultResponseBody
    public Page<RbacRoleDataPowerDo> page(Page<RbacRoleDataPowerDo> page, RbacRoleDataPowerDo rbacRoleDataPowerDo) {
        return rbacRoleDataPowerService.page(page, rbacRoleDataPowerDo);
    }

    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) RbacRoleDataPowerDo rbacRoleDataPowerDo) {
        return rbacRoleDataPowerService.save(rbacRoleDataPowerDo);
    }

    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) RbacRoleDataPowerDo rbacRoleDataPowerDo) {
        return rbacRoleDataPowerService.updateById(rbacRoleDataPowerDo);
    }

    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(Long id) {
        return rbacRoleDataPowerService.removeById(id);
    }
}
