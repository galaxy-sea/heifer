package plus.wcj.heifer.boot.controller.rbac.account;

import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.entity.rbac.account.RbacAccount;
import plus.wcj.heifer.boot.service.rbac.account.RbacAccountService;
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
 * 账户表 前端控制器
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@RestController
@RequestMapping("/rbac-account")
@RequiredArgsConstructor
    public class RbacAccountController {
    private final RbacAccountService rbacAccountService;

    /** id查询 */
    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacAccount getById(@NotNull Long id) {
        return rbacAccountService.get(id);
    }

    /** 分页查询 */
    @GetMapping
    @ResultResponseBody
    public Page<RbacAccount> page(Page<RbacAccount> page, RbacAccount rbacAccount, Tenant tenant) {
        return rbacAccountService.page(page, rbacAccount);
    }

    /** 保存 */
    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) RbacAccount rbacAccount, Tenant tenant) {
        return rbacAccountService.save(rbacAccount);
    }

    /** 修改 */
    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) RbacAccount rbacAccount) {
        return rbacAccountService.update(rbacAccount);
    }

    /** id删除 */
    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(@NotNull Long id) {
        return rbacAccountService.remove(id);
    }

    // TODO: 2021-11-22 changjin wei(魏昌进) 补充其他接口
    }
