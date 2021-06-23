package plus.wcj.heifer.boot.controller.rbac;

import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.entity.rbac.RbacOrgDo;
import plus.wcj.heifer.boot.service.rbac.RbacOrgService;
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
 * 租户 前端控制器
 * </p>
 *
 * @author changjinwei
 * @since 2021-06-23
 */
@RestController
@RequestMapping("/rbac-org-do")
@RequiredArgsConstructor
public class RbacOrgController {
    private final RbacOrgService rbacOrgService;

    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacOrgDo getById(Long id) {
        return rbacOrgService.getById(id);
    }

    @GetMapping
    @ResultResponseBody
    public Page<RbacOrgDo> page(Page<RbacOrgDo> page, RbacOrgDo rbacOrgDo) {
        return rbacOrgService.page(page, rbacOrgDo);
    }

    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) RbacOrgDo rbacOrgDo) {
        return rbacOrgService.save(rbacOrgDo);
    }

    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) RbacOrgDo rbacOrgDo) {
        return rbacOrgService.updateById(rbacOrgDo);
    }

    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(Long id) {
        return rbacOrgService.removeById(id);
    }
}
