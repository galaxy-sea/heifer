package plus.wcj.heifer.boot.controller.rbac.org;

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
import plus.wcj.heifer.boot.entity.rbac.org.RbacOrgAuthority;
import plus.wcj.heifer.boot.extension.validator.PostValid;
import plus.wcj.heifer.boot.extension.validator.PutValid;
import plus.wcj.heifer.boot.service.rbac.org.RbacOrgAuthorityService;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 租户拥有的权限 前端控制器
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@RestController
@RequestMapping("/rbac-org-authority")
@RequiredArgsConstructor
public class RbacOrgAuthorityController {
    private final RbacOrgAuthorityService rbacOrgAuthorityService;

    /** id查询 */
    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacOrgAuthority getById(@NotNull Long id) {
        return this.rbacOrgAuthorityService.get(id);
    }

    /** 分页查询 */
    @GetMapping
    @ResultResponseBody
    public Page<RbacOrgAuthority> page(Page<RbacOrgAuthority> page, RbacOrgAuthority rbacOrgAuthority) {
        return this.rbacOrgAuthorityService.page(page, rbacOrgAuthority);
    }

    /** 保存 */
    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) RbacOrgAuthority rbacOrgAuthority) {
        return this.rbacOrgAuthorityService.save(rbacOrgAuthority);
    }

    /** 修改 */
    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) RbacOrgAuthority rbacOrgAuthority) {
        return this.rbacOrgAuthorityService.update(rbacOrgAuthority);
    }

    /** id删除 */
    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(@NotNull Long id) {
        return this.rbacOrgAuthorityService.remove(id);
    }

    // TODO: 2021-07-03 changjin wei(魏昌进) 补充其他接口
}
