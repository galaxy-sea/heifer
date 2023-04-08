/*
 * Copyright 2021-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package plus.wcj.heifer.plugin.iam.security.support.mvc;

import plus.wcj.heifer.metadata.exception.ResultException;
import plus.wcj.heifer.metadata.exception.ResultStatusEnum;
import plus.wcj.heifer.metadata.iam.DataPowersDto;
import plus.wcj.heifer.metadata.iam.Tenant;
import plus.wcj.heifer.metadata.iam.UserPrincipalService;
import plus.wcj.heifer.plugin.iam.security.IamUserDetails;

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

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof IamUserDetails iamUserDetails) {
            DataPowersDto dataPowersDto = null;
            if (iamUserDetails.getTenantId() != null) {
                dataPowersDto = userPrincipalService.listPower(iamUserDetails.getTenantId(), iamUserDetails.getId());
            }
            dataPowersDto = DataPowersDto.init(dataPowersDto);
            return new Tenant(iamUserDetails.getId(),
                              iamUserDetails.getUsername(),
                              iamUserDetails.getTenantId(),
                              dataPowersDto.getDeptId(),
                              dataPowersDto.getDataPowers(),
                              dataPowersDto.getTenantDataPower(),
                              dataPowersDto.getDeptDataPower()
            );
        }
        throw new ResultException(ResultStatusEnum.UNAUTHORIZED);
    }
}
