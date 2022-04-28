package plus.wcj.heifer.plugin.iam.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import plus.wcj.heifer.metadata.annotation.PostValid;
import plus.wcj.heifer.metadata.annotation.PutValid;
import plus.wcj.heifer.metadata.annotation.ResultResponseBody;
import plus.wcj.heifer.metadata.tenant.Tenant;
import plus.wcj.heifer.plugin.iam.entity.IamAccount;
import plus.wcj.heifer.plugin.iam.service.IamAccountService;

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
 * 账户表 前端控制器
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-04-23
 */
@RestController
@RequestMapping("/iam-account")
@RequiredArgsConstructor
public class IamAccountController {
    private final IamAccountService iamAccountService;

    /** id查询 */
    @GetMapping(params = "id")
    @ResultResponseBody
    public IamAccount getById(@NotNull Long id) {
        return iamAccountService.get(id);
    }

    /** 分页查询 */
    @GetMapping
    @ResultResponseBody
    public Page<IamAccount> page(Page<IamAccount> page, IamAccount iamAccount, Tenant tenant) {
        return iamAccountService.page(page, iamAccount);
    }

    /** 保存 */
    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) IamAccount iamAccount, Tenant tenant) {
        return iamAccountService.save(iamAccount);
    }

    /** 修改 */
    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) IamAccount iamAccount) {
        return iamAccountService.update(iamAccount);
    }

    /** id删除 */
    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(@NotNull Long id) {
        return iamAccountService.remove(id);
    }

    // TODO: 2022-04-23 weichangjin (魏昌进) 补充其他接口
}
