package plus.wcj.heifer.boot.controller.rbac.role;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.common.mvc.result.ResultResponseBody;
import plus.wcj.heifer.boot.entity.rbac.role.RbacRoleDataPower;
import plus.wcj.heifer.boot.extension.validator.PostValid;
import plus.wcj.heifer.boot.extension.validator.PutValid;
import plus.wcj.heifer.boot.service.rbac.role.RbacRoleDataPowerService;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 角色数据权限 前端控制器
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@RestController
@RequestMapping("/rbac-role-data-power")
@RequiredArgsConstructor
public class RbacRoleDataPowerController {
    private final RbacRoleDataPowerService rbacRoleDataPowerService;

    /** id查询 */
    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacRoleDataPower getById(@NotNull Long id) {
        return this.rbacRoleDataPowerService.get(id);
    }

    /** 分页查询 */
    @GetMapping
    @ResultResponseBody
    public Page<RbacRoleDataPower> page(Page<RbacRoleDataPower> page, RbacRoleDataPower rbacRoleDataPower) {
        return this.rbacRoleDataPowerService.page(page, rbacRoleDataPower);
    }

    /** 保存 */
    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) RbacRoleDataPower rbacRoleDataPower) {
        return this.rbacRoleDataPowerService.save(rbacRoleDataPower);
    }

    /** 修改 */
    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) RbacRoleDataPower rbacRoleDataPower) {
        return this.rbacRoleDataPowerService.updateById(rbacRoleDataPower);
    }

    /** id删除 */
    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(@NotNull Long id) {
        return this.rbacRoleDataPowerService.remove(id);
    }

    // TODO: 2021-07-03 changjin wei(魏昌进) 补充其他接口
}
