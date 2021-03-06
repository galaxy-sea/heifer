package plus.wcj.heifer.boot.common.mvc.resolver;

import plus.wcj.heifer.boot.common.exception.ResultException;
import plus.wcj.heifer.boot.common.exception.ResultStatusEnum;
import plus.wcj.heifer.boot.common.security.userdetails.HeiferUserDetailsServiceImpl;
import plus.wcj.heifer.boot.common.security.userdetails.dto.UserPrincipal;
import plus.wcj.heifer.boot.extension.tenant.Tenant;

import lombok.RequiredArgsConstructor;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


/**
 * @author changjin wei(魏昌进)
 * @date 2021/4/25
 */
@RequiredArgsConstructor
public class TenantMethodArgumentResolver implements HandlerMethodArgumentResolver {


    private final HeiferUserDetailsServiceImpl heiferUserDetailsService;


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Tenant.class.isAssignableFrom(parameter.getParameterType());
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Tenant resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        UserPrincipal userDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails == null) {
            throw new ResultException(ResultStatusEnum.UNAUTHORIZED);
        }
        String allPower = "";
        if (userDetails.getTenantId() != null) {
            allPower = heiferUserDetailsService.getAllPower(userDetails.getTenantId(), userDetails.getId());
        }
        return new Tenant(userDetails.getId(),
                          userDetails.getUsername(),
                          userDetails.getTenantId(),
                          userDetails.getDeptId(),
                          allPower,
                          false
        );
    }
}
