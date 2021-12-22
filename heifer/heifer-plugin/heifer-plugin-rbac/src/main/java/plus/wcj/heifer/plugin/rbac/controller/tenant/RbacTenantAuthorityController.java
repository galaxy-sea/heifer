package plus.wcj.heifer.plugin.rbac.controller.tenant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import plus.wcj.heifer.matedata.annotation.PostValid;
import plus.wcj.heifer.matedata.annotation.PutValid;
import plus.wcj.heifer.matedata.annotation.ResultResponseBody;
import plus.wcj.heifer.matedata.tenant.Tenant;
import plus.wcj.heifer.plugin.rbac.entity.tenant.RbacTenantAuthority;
import plus.wcj.heifer.plugin.rbac.service.tenant.RbacTenantAuthorityService;

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
 * 租户拥有的权限 前端控制器
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@RestController
@RequestMapping("/rbac-tenant-authority")
@RequiredArgsConstructor
    public class RbacTenantAuthorityController {
    private final RbacTenantAuthorityService rbacTenantAuthorityService;

    /** id查询 */
    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacTenantAuthority getById(@NotNull Long id) {
        return rbacTenantAuthorityService.get(id);
    }

    /** 分页查询 */
    @GetMapping
    @ResultResponseBody
    public Page<RbacTenantAuthority> page(Page<RbacTenantAuthority> page, RbacTenantAuthority rbacTenantAuthority, Tenant tenant) {
        return rbacTenantAuthorityService.page(page, rbacTenantAuthority);
    }

    /** 保存 */
    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) RbacTenantAuthority rbacTenantAuthority, Tenant tenant) {
        return rbacTenantAuthorityService.save(rbacTenantAuthority);
    }

    /** 修改 */
    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) RbacTenantAuthority rbacTenantAuthority) {
        return rbacTenantAuthorityService.update(rbacTenantAuthority);
    }

    /** id删除 */
    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(@NotNull Long id) {
        return rbacTenantAuthorityService.remove(id);
    }

    // TODO: 2021-11-22 changjin wei(魏昌进) 补充其他接口
    }
