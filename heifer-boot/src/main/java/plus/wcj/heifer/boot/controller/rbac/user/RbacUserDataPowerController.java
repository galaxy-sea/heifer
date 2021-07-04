package plus.wcj.heifer.boot.controller.rbac.user;

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
import plus.wcj.heifer.boot.entity.rbac.user.RbacUserDataPower;
import plus.wcj.heifer.boot.extension.validator.PostValid;
import plus.wcj.heifer.boot.extension.validator.PutValid;
import plus.wcj.heifer.boot.service.rbac.user.RbacUserDataPowerService;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户数据权限 前端控制器
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@RestController
@RequestMapping("/rbac-user-data-power")
@RequiredArgsConstructor
public class RbacUserDataPowerController {
    private final RbacUserDataPowerService rbacUserDataPowerService;

    /** id查询 */
    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacUserDataPower getById(@NotNull Long id) {
        return rbacUserDataPowerService.get(id);
    }

    /** 分页查询 */
    @GetMapping
    @ResultResponseBody
    public Page<RbacUserDataPower> page(Page<RbacUserDataPower> page, RbacUserDataPower rbacUserDataPower) {
        return rbacUserDataPowerService.page(page, rbacUserDataPower);
    }

    /** 保存 */
    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) RbacUserDataPower rbacUserDataPower) {
        return rbacUserDataPowerService.save(rbacUserDataPower);
    }

    /** 修改 */
    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) RbacUserDataPower rbacUserDataPower) {
        return rbacUserDataPowerService.updateById(rbacUserDataPower);
    }

    /** id删除 */
    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(@NotNull Long id) {
        return rbacUserDataPowerService.remove(id);
    }

    // TODO: 2021-07-03 changjin wei(魏昌进) 补充其他接口
}
