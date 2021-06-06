package plus.wcj.heifer.boot.controller.rbac;

import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.entity.rbac.RbacUserPermissionRelDo;
import plus.wcj.heifer.boot.service.rbac.RbacUserPermissionRelService;
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
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * 用户功能权限表 前端控制器
 * </p>
 *
 * @author changjinwei
 * @since 2021-06-06
 */
@RestController
@RequestMapping("/rbac-user-permission-rel-do")
@RequiredArgsConstructor
public class RbacUserPermissionRelController {
    private final RbacUserPermissionRelService rbacUserPermissionRelService;

    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacUserPermissionRelDo getById(Long id) {
        return rbacUserPermissionRelService.getById(id);
    }

    @GetMapping
    @ResultResponseBody
    public Page<RbacUserPermissionRelDo> page(Page<RbacUserPermissionRelDo> page, RbacUserPermissionRelDo rbacUserPermissionRelDo) {
        return rbacUserPermissionRelService.page(page, rbacUserPermissionRelDo);
    }

    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) RbacUserPermissionRelDo rbacUserPermissionRelDo) {
        return rbacUserPermissionRelService.save(rbacUserPermissionRelDo);
    }

    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) RbacUserPermissionRelDo rbacUserPermissionRelDo) {
        return rbacUserPermissionRelService.updateById(rbacUserPermissionRelDo);
    }

    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(Long id) {
        return rbacUserPermissionRelService.removeById(id);
    }
}
