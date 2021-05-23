package plus.wcj.heifer.boot.controller.rbac;

import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.entity.rbac.RbacDeptDo;
import plus.wcj.heifer.boot.service.rbac.RbacDeptService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import plus.wcj.heifer.boot.common.mvc.result.ResultResponseBody;
import lombok.RequiredArgsConstructor;
/**
 * <p>
 * 部门 前端控制器
 * </p>
 *
 * @author changjinwei
 * @since 2021-05-23
 */
@RestController
@RequestMapping("/rbac-dept-do")
@RequiredArgsConstructor
public class RbacDeptController {
    private final RbacDeptService rbacDeptService;

    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacDeptDo getById(Long id) {
        return rbacDeptService.getById(id);
    }

    @GetMapping
    @ResultResponseBody
    public Page<RbacDeptDo> page(Page<RbacDeptDo> page, RbacDeptDo rbacDeptDo) {
        return rbacDeptService.page(page, new QueryWrapper<>(rbacDeptDo));
    }

    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody RbacDeptDo rbacDeptDo) {
        return rbacDeptService.save(rbacDeptDo);
    }

    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody RbacDeptDo rbacDeptDo) {
        return rbacDeptService.updateById(rbacDeptDo);
    }

    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(Long id) {
        return rbacDeptService.removeById(id);
    }
}
