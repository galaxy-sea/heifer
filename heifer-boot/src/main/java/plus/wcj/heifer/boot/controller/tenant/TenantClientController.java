package plus.wcj.heifer.boot.controller.tenant;

import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.entity.tenant.TenantClientDo;
import plus.wcj.heifer.boot.service.tenant.TenantClientService;
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
 * 客户端 前端控制器
 * </p>
 *
 * @author changjinwei
 * @since 2021-06-06
 */
@RestController
@RequestMapping("/tenant-client-do")
@RequiredArgsConstructor
public class TenantClientController {
    private final TenantClientService tenantClientService;

    @GetMapping(params = "id")
    @ResultResponseBody
    public TenantClientDo getById(Long id) {
        return tenantClientService.getById(id);
    }

    @GetMapping
    @ResultResponseBody
    public Page<TenantClientDo> page(Page<TenantClientDo> page, TenantClientDo tenantClientDo) {
        return tenantClientService.page(page, tenantClientDo);
    }

    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) TenantClientDo tenantClientDo) {
        return tenantClientService.save(tenantClientDo);
    }

    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) TenantClientDo tenantClientDo) {
        return tenantClientService.updateById(tenantClientDo);
    }

    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(Long id) {
        return tenantClientService.removeById(id);
    }
}
