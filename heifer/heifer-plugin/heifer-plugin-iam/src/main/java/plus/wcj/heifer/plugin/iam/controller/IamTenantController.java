package plus.wcj.heifer.plugin.iam.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import plus.wcj.heifer.metadata.annotation.PostValid;
import plus.wcj.heifer.metadata.annotation.PutValid;
import plus.wcj.heifer.metadata.annotation.ResultResponseBody;
import plus.wcj.heifer.metadata.tenant.Tenant;
import plus.wcj.heifer.plugin.iam.entity.IamTenant;
import plus.wcj.heifer.plugin.iam.service.IamTenantService;

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
 * 租户 前端控制器
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-04-23
 */
@RestController
@RequestMapping("/iam-tenant")
@RequiredArgsConstructor
public class IamTenantController {
    private final IamTenantService iamTenantService;

    /** id查询 */
    @GetMapping(params = "id")
    @ResultResponseBody
    public IamTenant getById(@NotNull Long id) {
        return iamTenantService.get(id);
    }

    /** 分页查询 */
    @GetMapping
    @ResultResponseBody
    public Page<IamTenant> page(Page<IamTenant> page, IamTenant iamTenant, Tenant tenant) {
        return iamTenantService.page(page, iamTenant);
    }

    /** 保存 */
    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) IamTenant iamTenant, Tenant tenant) {
        return iamTenantService.save(iamTenant);
    }

    /** 修改 */
    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) IamTenant iamTenant) {
        return iamTenantService.update(iamTenant);
    }

    /** id删除 */
    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(@NotNull Long id) {
        return iamTenantService.remove(id);
    }

    // TODO: 2022-04-23 weichangjin (魏昌进) 补充其他接口
}
