package plus.wcj.heifer.plugin.rbac.controller.tenant;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import plus.wcj.heifer.metadata.annotation.PostValid;
import plus.wcj.heifer.metadata.annotation.PutValid;
import plus.wcj.heifer.metadata.annotation.ResultResponseBody;
import plus.wcj.heifer.metadata.tenant.Tenant;
import plus.wcj.heifer.plugin.rbac.pojo.entity.tenant.RbacDept;
import plus.wcj.heifer.plugin.rbac.service.tenant.RbacDeptService;

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
 * 部门 前端控制器
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@RestController
@RequestMapping("/rbac-dept")
@RequiredArgsConstructor
public class RbacDeptController {
    private final RbacDeptService rbacDeptService;

    /** id查询 */
    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacDept getById(@NotNull Long id) {
        return rbacDeptService.get(id);
    }

    /** 分页查询 */
    @GetMapping
    @ResultResponseBody
    public Page<RbacDept> page(Page<RbacDept> page, RbacDept rbacDept, Tenant tenant) {
        return rbacDeptService.page(page, rbacDept);
    }

    /** 保存 */
    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) RbacDept rbacDept, Tenant tenant) {
        return rbacDeptService.save(rbacDept);
    }

    /** 修改 */
    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) RbacDept rbacDept) {
        return rbacDeptService.update(rbacDept);
    }

    /** id删除 */
    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(@NotNull Long id) {
        return rbacDeptService.remove(id);
    }

    // TODO: 2021-11-22 changjin wei(魏昌进) 补充其他接口
}
