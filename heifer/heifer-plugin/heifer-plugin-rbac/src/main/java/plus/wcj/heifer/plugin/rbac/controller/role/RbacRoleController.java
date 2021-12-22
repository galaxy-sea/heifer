package plus.wcj.heifer.plugin.rbac.controller.role;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import plus.wcj.heifer.matedata.annotation.PostValid;
import plus.wcj.heifer.matedata.annotation.PutValid;
import plus.wcj.heifer.matedata.annotation.ResultResponseBody;
import plus.wcj.heifer.matedata.tenant.Tenant;
import plus.wcj.heifer.plugin.rbac.entity.role.RbacRole;
import plus.wcj.heifer.plugin.rbac.service.role.RbacRoleService;

import lombok.RequiredArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@RestController
@RequestMapping("/rbac-role")
@RequiredArgsConstructor
    public class RbacRoleController {
    private final RbacRoleService rbacRoleService;

    /** id查询 */
    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacRole getById(@NotNull Long id) {
        return rbacRoleService.get(id);
    }

    /** 分页查询 */
    @GetMapping
    @ResultResponseBody
    public Page<RbacRole> page(Page<RbacRole> page, RbacRole rbacRole, Tenant tenant) {
        return rbacRoleService.page(page, rbacRole);
    }

    /** 保存 */
    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) RbacRole rbacRole, Tenant tenant) {
        return rbacRoleService.save(rbacRole);
    }

    /** 修改 */
    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) RbacRole rbacRole) {
        return rbacRoleService.update(rbacRole);
    }

    /** id删除 */
    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(@NotNull Long id) {
        return rbacRoleService.remove(id);
    }

    // TODO: 2021-11-22 changjin wei(魏昌进) 补充其他接口
    }
