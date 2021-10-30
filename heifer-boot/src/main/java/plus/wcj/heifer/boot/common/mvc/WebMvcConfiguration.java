package plus.wcj.heifer.boot.common.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import plus.wcj.heifer.boot.common.mvc.resolver.TenantMethodArgumentResolver;
import plus.wcj.heifer.boot.common.mvc.resolver.UserMethodArgumentResolver;

import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @date 2021/4/23
 */
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(0, new TenantMethodArgumentResolver());
        argumentResolvers.add(1, new UserMethodArgumentResolver());
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**");
    }
}
