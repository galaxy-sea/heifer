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
import plus.wcj.heifer.boot.entity.rbac.user.RbacUserAuthority;
import plus.wcj.heifer.boot.extension.validator.PostValid;
import plus.wcj.heifer.boot.extension.validator.PutValid;
import plus.wcj.heifer.boot.service.rbac.user.RbacUserAuthorityService;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户拥有功能权限表 前端控制器
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@RestController
@RequestMapping("/rbac-user-authority")
@RequiredArgsConstructor
public class RbacUserAuthorityController {
    private final RbacUserAuthorityService rbacUserAuthorityService;

    /** id查询 */
    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacUserAuthority getById(@NotNull Long id) {
        return this.rbacUserAuthorityService.get(id);
    }

    /** 分页查询 */
    @GetMapping
    @ResultResponseBody
    public Page<RbacUserAuthority> page(Page<RbacUserAuthority> page, RbacUserAuthority rbacUserAuthority) {
        return this.rbacUserAuthorityService.page(page, rbacUserAuthority);
    }

    /** 保存 */
    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) RbacUserAuthority rbacUserAuthority) {
        return this.rbacUserAuthorityService.save(rbacUserAuthority);
    }

    /** 修改 */
    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) RbacUserAuthority rbacUserAuthority) {
        return this.rbacUserAuthorityService.updateById(rbacUserAuthority);
    }

    /** id删除 */
    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(@NotNull Long id) {
        return this.rbacUserAuthorityService.remove(id);
    }

    // TODO: 2021-07-03 changjin wei(魏昌进) 补充其他接口
}
