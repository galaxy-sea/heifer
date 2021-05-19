package com.xkcoding.rbac.security.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 测试Controller
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-10 15:44
 */
@Slf4j
@RestController
@RequestMapping("/test/v2")
public class MyTestController {
    @GetMapping
    @PreAuthorize("hasAuthority('btn:monitor:online:query')")
    public String list() {
        log.info("测试列表查询");
        return "测试列表查询";
    }

    @PostMapping
    public String add() {
        log.info("测试列表添加");
        return "测试列表添加";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id) {
        log.info("测试列表修改");
        return "测试列表修改";
    }
}
