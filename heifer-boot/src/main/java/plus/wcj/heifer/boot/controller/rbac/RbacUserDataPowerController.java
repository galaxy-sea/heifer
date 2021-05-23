package plus.wcj.heifer.boot.controller.rbac;

import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.entity.rbac.RbacUserDataPowerDo;
import plus.wcj.heifer.boot.service.rbac.RbacUserDataPowerService;
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
 * 用户部门权限 前端控制器
 * </p>
 *
 * @author changjinwei
 * @since 2021-05-23
 */
@RestController
@RequestMapping("/rbac-user-data-power-do")
@RequiredArgsConstructor
public class RbacUserDataPowerController {
    private final RbacUserDataPowerService rbacUserDataPowerService;

    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacUserDataPowerDo getById(Long id) {
        return rbacUserDataPowerService.getById(id);
    }

    @GetMapping
    @ResultResponseBody
    public Page<RbacUserDataPowerDo> page(Page<RbacUserDataPowerDo> page, RbacUserDataPowerDo rbacUserDataPowerDo) {
        return rbacUserDataPowerService.page(page, new QueryWrapper<>(rbacUserDataPowerDo));
    }

    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody RbacUserDataPowerDo rbacUserDataPowerDo) {
        return rbacUserDataPowerService.save(rbacUserDataPowerDo);
    }

    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody RbacUserDataPowerDo rbacUserDataPowerDo) {
        return rbacUserDataPowerService.updateById(rbacUserDataPowerDo);
    }

    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(Long id) {
        return rbacUserDataPowerService.removeById(id);
    }
}
