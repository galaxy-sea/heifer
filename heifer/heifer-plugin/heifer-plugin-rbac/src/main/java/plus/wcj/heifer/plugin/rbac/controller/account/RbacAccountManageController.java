package plus.wcj.heifer.plugin.rbac.controller.account;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import plus.wcj.heifer.matedata.annotation.PostValid;
import plus.wcj.heifer.matedata.annotation.PutValid;
import plus.wcj.heifer.matedata.annotation.ResultResponseBody;
import plus.wcj.heifer.matedata.tenant.Tenant;
import plus.wcj.heifer.plugin.rbac.entity.account.RbacAccountManage;
import plus.wcj.heifer.plugin.rbac.service.account.RbacAccountManageService;

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
 * 账户与租户的绑定关系 前端控制器
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-11-22
 */
@RestController
@RequestMapping("/rbac-account-manage")
@RequiredArgsConstructor
    public class RbacAccountManageController {
    private final RbacAccountManageService rbacAccountManageService;

    /** id查询 */
    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacAccountManage getById(@NotNull Long id) {
        return rbacAccountManageService.get(id);
    }

    /** 分页查询 */
    @GetMapping
    @ResultResponseBody
    public Page<RbacAccountManage> page(Page<RbacAccountManage> page, RbacAccountManage rbacAccountManage, Tenant tenant) {
        return rbacAccountManageService.page(page, rbacAccountManage);
    }

    /** 保存 */
    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) RbacAccountManage rbacAccountManage, Tenant tenant) {
        return rbacAccountManageService.save(rbacAccountManage);
    }

    /** 修改 */
    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) RbacAccountManage rbacAccountManage) {
        return rbacAccountManageService.update(rbacAccountManage);
    }

    /** id删除 */
    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(@NotNull Long id) {
        return rbacAccountManageService.remove(id);
    }

    // TODO: 2021-11-22 changjin wei(魏昌进) 补充其他接口
    }
