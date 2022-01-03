package plus.wcj.heifer.plugin.rbac;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.context.annotation.ComponentScan;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/1/3
 */
@ComponentScan("plus.wcj.heifer.plugin.rbac")
@MapperScan("plus.wcj.heifer.plugin.rbac.dao")
public class RbacAutoConfiguration {
}
