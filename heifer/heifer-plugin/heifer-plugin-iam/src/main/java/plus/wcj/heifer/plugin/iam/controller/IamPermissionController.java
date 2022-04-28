package plus.wcj.heifer.plugin.iam.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import plus.wcj.heifer.metadata.annotation.PostValid;
import plus.wcj.heifer.metadata.annotation.PutValid;
import plus.wcj.heifer.metadata.annotation.ResultResponseBody;
import plus.wcj.heifer.metadata.tenant.Tenant;
import plus.wcj.heifer.plugin.iam.entity.IamPermission;
import plus.wcj.heifer.plugin.iam.service.IamPermissionService;

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
 * 功能权限 前端控制器
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-04-23
 */
@RestController
@RequestMapping("/iam-permission")
@RequiredArgsConstructor
public class IamPermissionController {
    private final IamPermissionService iamPermissionService;

    /** id查询 */
    @GetMapping(params = "id")
    @ResultResponseBody
    public IamPermission getById(@NotNull Long id) {
        return iamPermissionService.get(id);
    }

    /** 分页查询 */
    @GetMapping
    @ResultResponseBody
    public Page<IamPermission> page(Page<IamPermission> page, IamPermission iamPermission, Tenant tenant) {
        return iamPermissionService.page(page, iamPermission);
    }

    /** 保存 */
    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) IamPermission iamPermission, Tenant tenant) {
        return iamPermissionService.save(iamPermission);
    }

    /** 修改 */
    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) IamPermission iamPermission) {
        return iamPermissionService.update(iamPermission);
    }

    /** id删除 */
    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(@NotNull Long id) {
        return iamPermissionService.remove(id);
    }

    // TODO: 2022-04-23 weichangjin (魏昌进) 补充其他接口
}
