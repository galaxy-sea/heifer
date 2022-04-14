package plus.wcj.heifer.plugin.rbac.controller.account;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import plus.wcj.heifer.metadata.annotation.PostValid;
import plus.wcj.heifer.metadata.annotation.PutValid;
import plus.wcj.heifer.metadata.annotation.ResultResponseBody;
import plus.wcj.heifer.metadata.tenant.Tenant;
import plus.wcj.heifer.plugin.rbac.pojo.entity.account.RbacAccountAuthority;
import plus.wcj.heifer.plugin.rbac.service.account.RbacAccountAuthorityService;

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
 * 账户拥有功能权限表 前端控制器
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@RestController
@RequestMapping("/rbac-account-authority")
@RequiredArgsConstructor
public class RbacAccountAuthorityController {
    private final RbacAccountAuthorityService rbacAccountAuthorityService;

    /** id查询 */
    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacAccountAuthority getById(@NotNull Long id) {
        return rbacAccountAuthorityService.get(id);
    }

    /** 分页查询 */
    @GetMapping
    @ResultResponseBody
    public Page<RbacAccountAuthority> page(Page<RbacAccountAuthority> page, RbacAccountAuthority rbacAccountAuthority, Tenant tenant) {
        return rbacAccountAuthorityService.page(page, rbacAccountAuthority);
    }

    /** 保存 */
    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) RbacAccountAuthority rbacAccountAuthority, Tenant tenant) {
        return rbacAccountAuthorityService.save(rbacAccountAuthority);
    }

    /** 修改 */
    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) RbacAccountAuthority rbacAccountAuthority) {
        return rbacAccountAuthorityService.update(rbacAccountAuthority);
    }

    /** id删除 */
    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(@NotNull Long id) {
        return rbacAccountAuthorityService.remove(id);
    }

    // TODO: 2021-11-22 changjin wei(魏昌进) 补充其他接口
}
