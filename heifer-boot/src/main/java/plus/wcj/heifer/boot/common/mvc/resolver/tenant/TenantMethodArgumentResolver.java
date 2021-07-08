package plus.wcj.heifer.boot.common.mvc.resolver.tenant;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import plus.wcj.heifer.boot.common.exception.ResultException;
import plus.wcj.heifer.boot.common.exception.ResultStatus;
import plus.wcj.heifer.boot.common.security.userdetails.dto.UserPrincipal;


/**
 * @author changjin wei(魏昌进)
 * @date 2021/4/25
 */
@Component
public class TenantMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Tenant.class.isAssignableFrom(parameter.getParameterType());
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Tenant resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        UserPrincipal userDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userDetails == null) {
            throw new ResultException(ResultStatus.UNAUTHORIZED);
        }

        return new Tenant(userDetails.getId(),
                          userDetails.getUsername(),
                          userDetails.getOrgId(),
                          userDetails.getDeptId(),
                          userDetails.getDataPowers(),
                          userDetails.getAllPower()
                                   );
    }
}
