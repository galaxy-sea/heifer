package plus.wcj.heifer.boot.controller.tenant;

import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.entity.tenant.TenantOrgClientRelDo;
import plus.wcj.heifer.boot.service.tenant.TenantOrgClientRelService;
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
 * 租户客户端 前端控制器
 * </p>
 *
 * @author changjinwei
 * @since 2021-05-23
 */
@RestController
@RequestMapping("/tenant-org-client-rel-do")
@RequiredArgsConstructor
public class TenantOrgClientRelController {
    private final TenantOrgClientRelService tenantOrgClientRelService;

    @GetMapping(params = "id")
    @ResultResponseBody
    public TenantOrgClientRelDo getById(Long id) {
        return tenantOrgClientRelService.getById(id);
    }

    @GetMapping
    @ResultResponseBody
    public Page<TenantOrgClientRelDo> page(Page<TenantOrgClientRelDo> page, TenantOrgClientRelDo tenantOrgClientRelDo) {
        return tenantOrgClientRelService.page(page, new QueryWrapper<>(tenantOrgClientRelDo));
    }

    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody TenantOrgClientRelDo tenantOrgClientRelDo) {
        return tenantOrgClientRelService.save(tenantOrgClientRelDo);
    }

    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody TenantOrgClientRelDo tenantOrgClientRelDo) {
        return tenantOrgClientRelService.updateById(tenantOrgClientRelDo);
    }

    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(Long id) {
        return tenantOrgClientRelService.removeById(id);
    }
}
