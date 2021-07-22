package plus.wcj.heifer.boot.controller.rbac.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.common.mvc.result.ResultResponseBody;
import plus.wcj.heifer.boot.entity.rbac.user.RbacCustomer;
import plus.wcj.heifer.boot.extension.validator.PostValid;
import plus.wcj.heifer.boot.extension.validator.PutValid;
import plus.wcj.heifer.boot.service.rbac.user.RbacCustomerService;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 顾客信息 前端控制器
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@RestController
@RequestMapping("/rbac-customer")
@RequiredArgsConstructor
public class RbacCustomerController {
    private final RbacCustomerService rbacCustomerService;

    /** id查询 */
    @GetMapping(params = "id")
    @ResultResponseBody
    public RbacCustomer getById(@NotNull Long id) {
        return this.rbacCustomerService.get(id);
    }

    /** 分页查询 */
    @GetMapping
    @ResultResponseBody
    public Page<RbacCustomer> page(Page<RbacCustomer> page, RbacCustomer rbacCustomer) {
        return this.rbacCustomerService.page(page, rbacCustomer);
    }

    /** 保存 */
    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) RbacCustomer rbacCustomer) {
        return this.rbacCustomerService.save(rbacCustomer);
    }

    /** 修改 */
    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) RbacCustomer rbacCustomer) {
        return this.rbacCustomerService.updateById(rbacCustomer);
    }

    /** id删除 */
    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(@NotNull Long id) {
        return this.rbacCustomerService.remove(id);
    }

    // TODO: 2021-07-03 changjin wei(魏昌进) 补充其他接口
}
