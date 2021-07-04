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
import plus.wcj.heifer.boot.entity.rbac.user.RbacUser;
import plus.wcj.heifer.boot.extension.validator.PostValid;
import plus.wcj.heifer.boot.extension.validator.PutValid;
import plus.wcj.heifer.boot.service.rbac.user.RbacUserService;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@RestController
@RequestMapping("/rbac-user")
@RequiredArgsConstructor
public class RbacUserController {
    private final RbacUserService rbacUserService;

    /** id查询 */
    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacUser getById(@NotNull Long id) {
        return rbacUserService.get(id);
    }

    /** 分页查询 */
    @GetMapping
    @ResultResponseBody
    public Page<RbacUser> page(Page<RbacUser> page, RbacUser rbacUser) {
        return rbacUserService.page(page, rbacUser);
    }

    /** 保存 */
    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) RbacUser rbacUser) {
        return rbacUserService.save(rbacUser);
    }

    /** 修改 */
    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) RbacUser rbacUser) {
        return rbacUserService.updateById(rbacUser);
    }

    /** id删除 */
    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(@NotNull Long id) {
        return rbacUserService.remove(id);
    }

    // TODO: 2021-07-03 changjin wei(魏昌进) 补充其他接口
}
