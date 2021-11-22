package plus.wcj.heifer.boot.controller.rbac.tenant;

import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.entity.rbac.tenant.RbacTenantAuthority;
import plus.wcj.heifer.boot.service.rbac.tenant.RbacTenantAuthorityService;
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
import plus.wcj.heifer.boot.extension.tenant.Tenant;
import lombok.RequiredArgsConstructor;

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
