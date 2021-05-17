package com.xkcoding.rbac.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>
 * 启动器
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-10 11:28
 */
@SpringBootApplication
@MapperScan("com.xkcoding.rbac.security.repository")
public class SpringBootDemoRbacSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoRbacSecurityApplication.class, args);
    }
}
