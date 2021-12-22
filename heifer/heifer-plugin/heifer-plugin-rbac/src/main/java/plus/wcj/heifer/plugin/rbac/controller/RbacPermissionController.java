package plus.wcj.heifer.plugin.rbac.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import plus.wcj.heifer.matedata.annotation.PostValid;
import plus.wcj.heifer.matedata.annotation.PutValid;
import plus.wcj.heifer.matedata.annotation.ResultResponseBody;
import plus.wcj.heifer.matedata.tenant.Tenant;
import plus.wcj.heifer.plugin.rbac.entity.RbacPermission;
import plus.wcj.heifer.plugin.rbac.service.RbacPermissionService;

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
 * 功能权限 前端控制器
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@RestController
@RequestMapping("/rbac-permission")
@RequiredArgsConstructor
    public class RbacPermissionController {
    private final RbacPermissionService rbacPermissionService;

    /** id查询 */
    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacPermission getById(@NotNull Long id) {
        return rbacPermissionService.get(id);
    }

    /** 分页查询 */
    @GetMapping
    @ResultResponseBody
    public Page<RbacPermission> page(Page<RbacPermission> page, RbacPermission rbacPermission, Tenant tenant) {
        return rbacPermissionService.page(page, rbacPermission);
    }

    /** 保存 */
    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) RbacPermission rbacPermission, Tenant tenant) {
        return rbacPermissionService.save(rbacPermission);
    }

    /** 修改 */
    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) RbacPermission rbacPermission) {
        return rbacPermissionService.update(rbacPermission);
    }

    /** id删除 */
    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(@NotNull Long id) {
        return rbacPermissionService.remove(id);
    }

    // TODO: 2021-11-22 changjin wei(魏昌进) 补充其他接口
    }
