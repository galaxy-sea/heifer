package plus.wcj.heifer.plugin.iam.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import plus.wcj.heifer.metadata.annotation.PostValid;
import plus.wcj.heifer.metadata.annotation.PutValid;
import plus.wcj.heifer.metadata.annotation.ResultResponseBody;
import plus.wcj.heifer.metadata.tenant.Tenant;
import plus.wcj.heifer.plugin.iam.entity.IamDept;
import plus.wcj.heifer.plugin.iam.service.IamDeptService;

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
 * 部门 前端控制器
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-04-23
 */
@RestController
@RequestMapping("/iam-dept")
@RequiredArgsConstructor
public class IamDeptController {
    private final IamDeptService iamDeptService;

    /** id查询 */
    @GetMapping(params = "id")
    @ResultResponseBody
    public IamDept getById(@NotNull Long id) {
        return iamDeptService.get(id);
    }

    /** 分页查询 */
    @GetMapping
    @ResultResponseBody
    public Page<IamDept> page(Page<IamDept> page, IamDept iamDept, Tenant tenant) {
        return iamDeptService.page(page, iamDept);
    }

    /** 保存 */
    @PostMapping
    @ResultResponseBody
    public boolean save(@RequestBody @Validated(value = PostValid.class) IamDept iamDept, Tenant tenant) {
        return iamDeptService.save(iamDept);
    }

    /** 修改 */
    @PutMapping
    @ResultResponseBody
    public boolean updateById(@RequestBody @Validated(value = PutValid.class) IamDept iamDept) {
        return iamDeptService.update(iamDept);
    }

    /** id删除 */
    @DeleteMapping(params = "id")
    @ResultResponseBody
    public boolean removeById(@NotNull Long id) {
        return iamDeptService.remove(id);
    }

    // TODO: 2022-04-23 weichangjin (魏昌进) 补充其他接口
}
