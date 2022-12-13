package plus.wcj.heifer.common.swagger.examples;

import plus.wcj.heifer.metadata.annotation.IgnoreWebSecurity;
import plus.wcj.heifer.metadata.annotation.ResponseBodyResult;
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
    @ResponseBodyResult
    public Tenant test1(Tenant tenant) {
        return tenant;
    }


    @GetMapping("permission")
    @ResponseBodyResult
    @PreAuthorize("hasAuthority('heifer:admin:test')")
    public Tenant test2(Tenant tenant) {
        return tenant;
    }

    @GetMapping("role")
    @ResponseBodyResult
    @PreAuthorize("hasRole('admin')")
    public Tenant testRole(Tenant tenant) {
        return tenant;
    }

    @GetMapping("not")
    @ResponseBodyResult
    @PreAuthorize("hasAuthority('admin123123')")
    public Tenant testUser(Tenant tenant) {
        return tenant;
    }

    @RequestMapping(value = {"ignoreWebSecurity", "wodesijie"})
    @ResponseBodyResult
    @IgnoreWebSecurity
    public String ignoreWebSecurity() {
        return "IgnoreWebSecurity";
    }


}
