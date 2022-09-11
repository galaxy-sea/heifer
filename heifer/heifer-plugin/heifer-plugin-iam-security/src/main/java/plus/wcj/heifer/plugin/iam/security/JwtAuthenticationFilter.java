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

package plus.wcj.heifer.plugin.iam.security;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.nimbusds.jwt.JWTClaimsSet;
import plus.wcj.heifer.common.security.filter.IamOncePerRequestFilter;
import plus.wcj.heifer.metadata.exception.ResultException;
import plus.wcj.heifer.metadata.exception.ResultStatusEnum;
import plus.wcj.heifer.metadata.properties.JwtProperties;
import plus.wcj.heifer.metadata.tenant.UserPrincipalService;
import plus.wcj.heifer.tools.util.JwtUtils;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/12/21
 */
public class JwtAuthenticationFilter extends IamOncePerRequestFilter {
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final JwtProperties jwtProperties;
    private final UserPrincipalService userPrincipalService;

    private final List<UserPrincipalCustomizeService> UserPrincipalCustomizeServiceLists;

    private final Cache<String, UserPrincipal> cache = Caffeine.newBuilder()
                                                               .expireAfterAccess(10, TimeUnit.MINUTES)
                                                               .maximumSize(10000)
                                                               .build();


    public JwtAuthenticationFilter(HandlerExceptionResolver handlerExceptionResolver, JwtProperties jwtProperties, UserPrincipalService userPrincipalService, List<UserPrincipalCustomizeService> userPrincipalCustomizeServiceLists) {
        this.handlerExceptionResolver = handlerExceptionResolver;
        this.jwtProperties = jwtProperties;
        this.userPrincipalService = userPrincipalService;
        this.UserPrincipalCustomizeServiceLists = userPrincipalCustomizeServiceLists;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (StringUtils.hasText(authorization)) {
                String tenantId = request.getHeader(Constant.TENANT_ID);
                UsernamePasswordAuthenticationToken authentication = this.toAuthentication(authorization, tenantId);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        }
    }

    /**
     * 获取 UsernamePasswordAuthenticationToken 对象
     * <p>
     * 每次都会刷新一下 Permissions 权限,保持 权限是最新的
     *
     * @param authorization token
     * @param tenantId 租户id
     *
     * @return UsernamePasswordAuthenticationToken对象
     */
    private UsernamePasswordAuthenticationToken toAuthentication(String authorization, String tenantId) {

        String cacheKey = authorization + ":" + tenantId;

        UserPrincipal userPrincipal = cache.get(cacheKey, key -> {
            JWTClaimsSet jwtClaimsSet = JwtUtils.parseAuthorization(authorization, jwtProperties.getKey());
            return this.getUserPrincipal(jwtClaimsSet, tenantId);
        });

        if (JwtUtils.isValidExp(userPrincipal.getExpirationTime(), new Date())) {
            cache.invalidate(cacheKey);
            throw new ResultException(ResultStatusEnum.EXPIRED_TOKEN);
        }

        if (userPrincipal.getTenantId() != null) {
            List<String> allPermission = userPrincipalService.listPermission(userPrincipal.getId(), userPrincipal.getTenantId());
            userPrincipal.setPermissions(allPermission);
        }

        for (UserPrincipalCustomizeService customizeService : UserPrincipalCustomizeServiceLists) {
            customizeService.customizeUserPrincipal(userPrincipal);
        }
        return new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
    }


    /**
     * 获取{@link UserPrincipal}
     *
     * @param jwtClaimsSet JSON Web Token (JWT) claims set.
     * @param tenantId_ 租户id 可以为null
     *
     * @return UserPrincipal
     */
    private UserPrincipal getUserPrincipal(JWTClaimsSet jwtClaimsSet, String tenantId_) {
        Date expirationTime = jwtClaimsSet.getExpirationTime();
        Long accountId = Long.valueOf(jwtClaimsSet.getJWTID());
        String username = jwtClaimsSet.getSubject();
        Long tenantId = StringUtils.hasText(tenantId_) ? null : NumberUtils.parseNumber(tenantId_, Long.class);
        return new UserPrincipal(accountId, username, tenantId, true, expirationTime);
    }
}
