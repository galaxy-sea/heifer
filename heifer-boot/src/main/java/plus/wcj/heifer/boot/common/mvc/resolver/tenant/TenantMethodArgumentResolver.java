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

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        UserPrincipal userDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userDetails == null) {
            throw new ResultException(ResultStatus.UNAUTHORIZED);
        }

        Tenant tenant = new Tenant().setUserId(userDetails.getId())
                                    .setUsername(userDetails.getUsername())
                                    .setOrgId(userDetails.getOrgId())
                                    .setDeptId(userDetails.getDeptId())
                                    .setDataPowers(userDetails.getDataPowers());
        // .authority(userDetails.getAuthorities())
        // TODO: 2021/6/28 changjin wei(魏昌进) 考虑是否可以增加Authorities

        return tenant;
    }
}
