package plus.wcj.heifer.boot.controller.rbac.account;

import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.entity.rbac.account.RbacAccountRoleRel;
import plus.wcj.heifer.boot.service.rbac.account.RbacAccountRoleRelService;
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
 * 账户拥有角色关系表 前端控制器
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@RestController
@RequestMapping("/rbac-account-role-rel")
@RequiredArgsConstructor
    public class RbacAccountRoleRelController {
    private final RbacAccountRoleRelService rbacAccountRoleRelService;

    /** id查询 */
    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacAccountRoleRel getById(@NotNull Long id) {
        return rbacAccountRoleRelService.get(id);
    }

    /** 分页查询 */
    @GetMapping
    @ResultResponseBody
    public Page<RbacAccountRoleRel> page(Page<RbacAccountRoleRel> page, RbacAccountRoleRel rbacAccountRoleRel, Tenant tenant) {
        return rbacAccountRoleRelService.page(page, rbacAccountRoleRel);
    }

    /** 保存 */
    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) RbacAccountRoleRel rbacAccountRoleRel, Tenant tenant) {
        return rbacAccountRoleRelService.save(rbacAccountRoleRel);
    }

    /** 修改 */
    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) RbacAccountRoleRel rbacAccountRoleRel) {
        return rbacAccountRoleRelService.update(rbacAccountRoleRel);
    }

    /** id删除 */
    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(@NotNull Long id) {
        return rbacAccountRoleRelService.remove(id);
    }

    // TODO: 2021-11-22 changjin wei(魏昌进) 补充其他接口
    }
