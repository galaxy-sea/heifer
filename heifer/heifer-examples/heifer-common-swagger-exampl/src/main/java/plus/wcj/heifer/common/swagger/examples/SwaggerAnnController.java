package plus.wcj.heifer.common.swagger.examples;

import com.baomidou.mybatisplus.annotation.OrderBy;
import com.baomidou.mybatisplus.core.metadata.IPage;
import plus.wcj.heifer.common.mybatisplus.validation.OrderByValid;
import plus.wcj.heifer.metadata.annotation.IgnoreWebSecurity;
import plus.wcj.heifer.metadata.annotation.ResultResponseBody;
import plus.wcj.heifer.metadata.tenant.Tenant;

import io.swagger.annotations.ApiOperation;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/6/21
 */
@RestController
@RequestMapping("security")
@ApiOperation("data")
public class SwaggerAnnController {

    @GetMapping
    public String index() {
        return "123123";
    }

    @GetMapping("sign")
    @ResultResponseBody
    public Tenant test1(Tenant tenant) {
        return tenant;
    }


    @GetMapping("permission")
    @ResultResponseBody
    @PreAuthorize("hasAuthority('heifer:admin:test')")
    public Tenant test2(Tenant tenant) {
        return tenant;
    }

    @GetMapping("role")
    @ResultResponseBody
    @PreAuthorize("hasRole('admin')")
    public Tenant testRole(Tenant tenant) {
        return tenant;
    }

    @GetMapping("not")
    @ResultResponseBody
    @PreAuthorize("hasAuthority('admin123123')")
    public Tenant testUser(Tenant tenant, Tenant tenant2, Tenant tenant3, Tenant tenant4, @OrderByValid IPage<Tenant> page) {
        return tenant;
    }

    @RequestMapping(value = {"ignoreWebSecurity", "wodesijie"})
    @ResultResponseBody
    @IgnoreWebSecurity
    public String ignoreWebSecurity() {
        return "IgnoreWebSecurity";
    }


}
