package plus.wcj.heifer.boot.controller.tenant;

import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.entity.tenant.TenantOrgDo;
import plus.wcj.heifer.boot.service.tenant.TenantOrgService;
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
 * @since 2021-06-06
 */
@RestController
@RequestMapping("/tenant-org-do")
@RequiredArgsConstructor
public class TenantOrgController {
    private final TenantOrgService tenantOrgService;

    @GetMapping(params = "id")
    @ResultResponseBody
    public TenantOrgDo getById(Long id) {
        return tenantOrgService.getById(id);
    }

    @GetMapping
    @ResultResponseBody
    public Page<TenantOrgDo> page(Page<TenantOrgDo> page, TenantOrgDo tenantOrgDo) {
        return tenantOrgService.page(page, tenantOrgDo);
    }

    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) TenantOrgDo tenantOrgDo) {
        return tenantOrgService.save(tenantOrgDo);
    }

    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) TenantOrgDo tenantOrgDo) {
        return tenantOrgService.updateById(tenantOrgDo);
    }

    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(Long id) {
        return tenantOrgService.removeById(id);
    }
}
