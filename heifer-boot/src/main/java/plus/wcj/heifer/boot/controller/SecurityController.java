package plus.wcj.heifer.boot.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.common.mvc.result.ResultResponseBody;

import java.util.Date;

@RestController
@RequestMapping("security")
// @PreAuthorize("hasAuthority('page:not')")
public class SecurityController {

    @GetMapping("1")
    @ResultResponseBody
    public Date test1(){
        return new Date();
    }


    @GetMapping("2")
    @ResultResponseBody
    @PreAuthorize("hasAuthority('page:test')")
    public Date test2(){
        return new Date();
    }

    @GetMapping("testRole")
    @ResultResponseBody
    @PreAuthorize("hasAuthority('page:test:role')")
    public Date testRole(){
        return new Date();
    }

    @GetMapping("testUser")
    @ResultResponseBody
    @PreAuthorize("hasAuthority('page:test:user')")
    public Date testUser(){
        return new Date();
    }

    @GetMapping("3")
    @ResultResponseBody
    @PreAuthorize("hasAuthority('page:not')")
    public Date test3(){
        return new Date();
    }
}
