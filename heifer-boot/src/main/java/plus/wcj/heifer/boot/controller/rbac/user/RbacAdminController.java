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
import plus.wcj.heifer.boot.entity.rbac.user.RbacAdmin;
import plus.wcj.heifer.boot.extension.validator.PostValid;
import plus.wcj.heifer.boot.extension.validator.PutValid;
import plus.wcj.heifer.boot.service.rbac.user.RbacAdminService;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 管理员信息 前端控制器
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@RestController
@RequestMapping("/rbac-admin")
@RequiredArgsConstructor
public class RbacAdminController {
    private final RbacAdminService rbacAdminService;

    /** id查询 */
    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacAdmin getById(@NotNull Long id) {
        return this.rbacAdminService.get(id);
    }

    /** 分页查询 */
    @GetMapping
    @ResultResponseBody
    public Page<RbacAdmin> page(Page<RbacAdmin> page, RbacAdmin rbacAdmin) {
        return this.rbacAdminService.page(page, rbacAdmin);
    }

    /** 保存 */
    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) RbacAdmin rbacAdmin) {
        return this.rbacAdminService.save(rbacAdmin);
    }

    /** 修改 */
    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) RbacAdmin rbacAdmin) {
        return this.rbacAdminService.updateById(rbacAdmin);
    }

    /** id删除 */
    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(@NotNull Long id) {
        return this.rbacAdminService.remove(id);
    }

    // TODO: 2021-07-03 changjin wei(魏昌进) 补充其他接口
}
