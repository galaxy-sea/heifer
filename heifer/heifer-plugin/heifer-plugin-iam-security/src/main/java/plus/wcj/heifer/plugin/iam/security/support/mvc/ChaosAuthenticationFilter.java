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

import plus.wcj.heifer.common.security.filter.IamOncePerRequestFilter;
import plus.wcj.heifer.plugin.iam.security.ChaosUserDetails;
import plus.wcj.heifer.plugin.iam.security.Constant;
import plus.wcj.heifer.plugin.iam.security.support.registry.ChaosMetadata;
import plus.wcj.heifer.plugin.iam.security.util.ChaosUtils;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/9/17
 */
public class ChaosAuthenticationFilter extends IamOncePerRequestFilter {

    private final HandlerExceptionResolver handlerExceptionResolver;

    private final ChaosMetadata chaosMetadata;

    public ChaosAuthenticationFilter(HandlerExceptionResolver handlerExceptionResolver, ChaosMetadata chaosMetadata) {
        this.handlerExceptionResolver = handlerExceptionResolver;
        this.chaosMetadata = chaosMetadata;
    }


    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (StringUtils.hasText(authorization) && authorization.startsWith(Constant.CHAOS_PREFIX)) {
                UsernamePasswordAuthenticationToken authentication = this.toAuthentication(authorization);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            handlerExceptionResolver.resolveException(request, response, null, e);
        }
    }

    private UsernamePasswordAuthenticationToken toAuthentication(String authorization) {
        String chaos = chaosMetadata.getChaos();
        authorization = authorization.substring(Constant.CHAOS_PREFIX.length());
        if (ChaosUtils.decode(authorization, chaosMetadata.getChaos())) {

        }

        ChaosUserDetails chaosUserDetails = new ChaosUserDetails();
        return new UsernamePasswordAuthenticationToken(chaosUserDetails, null, chaosUserDetails.getAuthorities());
    }
}
