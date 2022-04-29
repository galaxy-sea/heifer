package plus.wcj.heifer.plugin.iam.security.support;

import plus.wcj.heifer.common.security.UserPrincipal;
import plus.wcj.heifer.metadata.exception.ResultException;
import plus.wcj.heifer.metadata.exception.ResultStatusEnum;
import plus.wcj.heifer.metadata.tenant.DataPowersDto;
import plus.wcj.heifer.metadata.tenant.Tenant;
import plus.wcj.heifer.metadata.tenant.UserPrincipalService;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/3/13
 */
public class TenantHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private final UserPrincipalService userPrincipalService;

    public TenantHandlerMethodArgumentResolver(UserPrincipalService userPrincipalService) {
        this.userPrincipalService = userPrincipalService;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Tenant.class.isAssignableFrom(parameter.getParameterType());
    }

    @SuppressWarnings("NullableProblems")
    @Override
    public Tenant resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userPrincipal == null) {
            throw new ResultException(ResultStatusEnum.UNAUTHORIZED);
        }
        DataPowersDto dataPowersDto = null;
        if (userPrincipal.getTenantId() != null) {
            dataPowersDto = userPrincipalService.listPower(userPrincipal.getTenantId(), userPrincipal.getId());
        }
        dataPowersDto = DataPowersDto.init(dataPowersDto);
        return new Tenant(userPrincipal.getId(),
                          userPrincipal.getUsername(),
                          userPrincipal.getTenantId(),
                          dataPowersDto.getDeptId(),
                          dataPowersDto.getDataPowers(),
                          dataPowersDto.getTenantDataPower(),
                          dataPowersDto.getDeptDataPower()
        );
    }
}
