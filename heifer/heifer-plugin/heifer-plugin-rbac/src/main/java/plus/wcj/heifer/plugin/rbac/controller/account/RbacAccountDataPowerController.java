package plus.wcj.heifer.plugin.rbac.controller.account;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import plus.wcj.heifer.matedata.annotation.PostValid;
import plus.wcj.heifer.matedata.annotation.PutValid;
import plus.wcj.heifer.matedata.annotation.ResultResponseBody;
import plus.wcj.heifer.matedata.tenant.Tenant;
import plus.wcj.heifer.plugin.rbac.pojo.entity.account.RbacAccountDataPower;
import plus.wcj.heifer.plugin.rbac.service.account.RbacAccountDataPowerService;

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
