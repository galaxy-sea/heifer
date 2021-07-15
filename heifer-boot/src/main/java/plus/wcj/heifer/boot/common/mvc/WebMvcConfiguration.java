package plus.wcj.heifer.boot.common.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
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

    // WebMvcConfigurer start

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new TenantMethodArgumentResolver());
        argumentResolvers.add(new UserMethodArgumentResolver());
    }


    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf(httpMessageConverter -> StringHttpMessageConverter.class.equals(httpMessageConverter.getClass()));
    }


}
