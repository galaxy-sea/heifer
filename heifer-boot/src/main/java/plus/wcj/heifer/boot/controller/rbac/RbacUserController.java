package plus.wcj.heifer.boot.controller.rbac;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.common.mvc.result.ResultResponseBody;
import plus.wcj.heifer.boot.entity.rbac.RbacUserDo;
import plus.wcj.heifer.boot.service.rbac.RbacUserService;
/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author changjinwei
 * @since 2021-05-23
 */
@RestController
@RequestMapping("/rbac-user-do")
@RequiredArgsConstructor
public class RbacUserController {
    private final RbacUserService rbacUserService;

    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacUserDo getById(Long id) {
        return rbacUserService.getById(id);
    }

    @GetMapping
    @ResultResponseBody
    public Page<RbacUserDo> page(Page<RbacUserDo> page, RbacUserDo rbacUserDo) {
        return rbacUserService.page(page, new QueryWrapper<>(rbacUserDo));
    }

    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody RbacUserDo rbacUserDo) {
        return rbacUserService.save(rbacUserDo);
    }

    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody RbacUserDo rbacUserDo) {
        return rbacUserService.updateById(rbacUserDo);
    }

    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(Long id) {
        return rbacUserService.removeById(id);
    }
}
