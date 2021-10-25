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
import plus.wcj.heifer.boot.entity.rbac.user.RbacUserRoleRel;
import plus.wcj.heifer.boot.extension.validator.PostValid;
import plus.wcj.heifer.boot.extension.validator.PutValid;
import plus.wcj.heifer.boot.service.rbac.user.RbacUserRoleRelService;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户拥有角色关系表 前端控制器
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@RestController
@RequestMapping("/rbac-user-role-rel")
@RequiredArgsConstructor
public class RbacUserRoleRelController {
    private final RbacUserRoleRelService rbacUserRoleRelService;

    /** id查询 */
    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacUserRoleRel getById(@NotNull Long id) {
        return this.rbacUserRoleRelService.get(id);
    }

    /** 分页查询 */
    @GetMapping
    @ResultResponseBody
    public Page<RbacUserRoleRel> page(Page<RbacUserRoleRel> page, RbacUserRoleRel rbacUserRoleRel) {
        return this.rbacUserRoleRelService.page(page, rbacUserRoleRel);
    }

    /** 保存 */
    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) RbacUserRoleRel rbacUserRoleRel) {
        return this.rbacUserRoleRelService.save(rbacUserRoleRel);
    }

    /** 修改 */
    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) RbacUserRoleRel rbacUserRoleRel) {
        return this.rbacUserRoleRelService.update(rbacUserRoleRel);
    }

    /** id删除 */
    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(@NotNull Long id) {
        return this.rbacUserRoleRelService.remove(id);
    }

    // TODO: 2021-07-03 changjin wei(魏昌进) 补充其他接口
}
