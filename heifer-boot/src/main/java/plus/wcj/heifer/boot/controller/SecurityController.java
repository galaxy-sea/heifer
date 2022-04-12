package plus.wcj.heifer.boot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import plus.wcj.heifer.boot.common.security.properties.IgnoreWebSecurity;
import plus.wcj.heifer.boot.extension.tenant.Tenant;
import plus.wcj.heifer.boot.common.mvc.result.ResultResponseBody;

import java.util.Date;

/**
 * @author changjin wei(魏昌进)
 */
@RestController
@RequestMapping("security")
public class SecurityController {

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
    public Tenant testUser(Tenant tenant) {
        return tenant;
    }

    @RequestMapping(value = {"ignoreWebSecurity","wodesijie"})
    @ResultResponseBody
    @IgnoreWebSecurity
    public String ignoreWebSecurity() {
        return "IgnoreWebSecurity";
    }


}
