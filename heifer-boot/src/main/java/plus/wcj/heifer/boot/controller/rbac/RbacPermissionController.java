package plus.wcj.heifer.boot.controller.rbac;

import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.entity.rbac.RbacPermissionDo;
import plus.wcj.heifer.boot.service.rbac.RbacPermissionService;
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
 * 功能权限 前端控制器
 * </p>
 *
 * @author changjinwei
 * @since 2021-06-06
 */
@RestController
@RequestMapping("/rbac-permission-do")
@RequiredArgsConstructor
public class RbacPermissionController {
    private final RbacPermissionService rbacPermissionService;

    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacPermissionDo getById(Long id) {
        return rbacPermissionService.getById(id);
    }

    @GetMapping
    @ResultResponseBody
    public Page<RbacPermissionDo> page(Page<RbacPermissionDo> page, RbacPermissionDo rbacPermissionDo) {
        return rbacPermissionService.page(page, rbacPermissionDo);
    }

    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) RbacPermissionDo rbacPermissionDo) {
        return rbacPermissionService.save(rbacPermissionDo);
    }

    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) RbacPermissionDo rbacPermissionDo) {
        return rbacPermissionService.updateById(rbacPermissionDo);
    }

    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(Long id) {
        return rbacPermissionService.removeById(id);
    }
}
