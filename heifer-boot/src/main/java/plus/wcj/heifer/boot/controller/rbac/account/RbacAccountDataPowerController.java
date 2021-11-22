package plus.wcj.heifer.boot.controller.rbac.account;

import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.entity.rbac.account.RbacAccountDataPower;
import plus.wcj.heifer.boot.service.rbac.account.RbacAccountDataPowerService;
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
 * 账户数据权限 前端控制器
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@RestController
@RequestMapping("/rbac-account-data-power")
@RequiredArgsConstructor
    public class RbacAccountDataPowerController {
    private final RbacAccountDataPowerService rbacAccountDataPowerService;

    /** id查询 */
    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacAccountDataPower getById(@NotNull Long id) {
        return rbacAccountDataPowerService.get(id);
    }

    /** 分页查询 */
    @GetMapping
    @ResultResponseBody
    public Page<RbacAccountDataPower> page(Page<RbacAccountDataPower> page, RbacAccountDataPower rbacAccountDataPower, Tenant tenant) {
        return rbacAccountDataPowerService.page(page, rbacAccountDataPower);
    }

    /** 保存 */
    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) RbacAccountDataPower rbacAccountDataPower, Tenant tenant) {
        return rbacAccountDataPowerService.save(rbacAccountDataPower);
    }

    /** 修改 */
    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) RbacAccountDataPower rbacAccountDataPower) {
        return rbacAccountDataPowerService.update(rbacAccountDataPower);
    }

    /** id删除 */
    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(@NotNull Long id) {
        return rbacAccountDataPowerService.remove(id);
    }

    // TODO: 2021-11-22 changjin wei(魏昌进) 补充其他接口
    }
