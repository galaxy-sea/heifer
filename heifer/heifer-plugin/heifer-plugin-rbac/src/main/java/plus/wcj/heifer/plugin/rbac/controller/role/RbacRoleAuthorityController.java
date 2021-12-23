package plus.wcj.heifer.plugin.rbac.controller.role;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import plus.wcj.heifer.matedata.annotation.PostValid;
import plus.wcj.heifer.matedata.annotation.PutValid;
import plus.wcj.heifer.matedata.annotation.ResultResponseBody;
import plus.wcj.heifer.matedata.tenant.Tenant;
import plus.wcj.heifer.plugin.rbac.pojo.entity.role.RbacRoleAuthority;
import plus.wcj.heifer.plugin.rbac.service.role.RbacRoleAuthorityService;

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
 * 角色拥有功能权限关系表 前端控制器
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@RestController
@RequestMapping("/rbac-role-authority")
@RequiredArgsConstructor
public class RbacRoleAuthorityController {
    private final RbacRoleAuthorityService rbacRoleAuthorityService;

    /** id查询 */
    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacRoleAuthority getById(@NotNull Long id) {
        return rbacRoleAuthorityService.get(id);
    }

    /** 分页查询 */
    @GetMapping
    @ResultResponseBody
    public Page<RbacRoleAuthority> page(Page<RbacRoleAuthority> page, RbacRoleAuthority rbacRoleAuthority, Tenant tenant) {
        return rbacRoleAuthorityService.page(page, rbacRoleAuthority);
    }

    /** 保存 */
    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) RbacRoleAuthority rbacRoleAuthority, Tenant tenant) {
        return rbacRoleAuthorityService.save(rbacRoleAuthority);
    }

    /** 修改 */
    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) RbacRoleAuthority rbacRoleAuthority) {
        return rbacRoleAuthorityService.update(rbacRoleAuthority);
    }

    /** id删除 */
    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(@NotNull Long id) {
        return rbacRoleAuthorityService.remove(id);
    }

    // TODO: 2021-11-22 changjin wei(魏昌进) 补充其他接口
}
