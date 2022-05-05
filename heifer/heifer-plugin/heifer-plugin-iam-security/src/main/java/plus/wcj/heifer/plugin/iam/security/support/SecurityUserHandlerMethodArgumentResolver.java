package plus.wcj.heifer.plugin.iam.security.support;

import plus.wcj.heifer.plugin.iam.security.UserPrincipal;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/3/13
 */
public class SecurityUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return UserDetails.class.isAssignableFrom(parameter.getParameterType());
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public UserPrincipal resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
