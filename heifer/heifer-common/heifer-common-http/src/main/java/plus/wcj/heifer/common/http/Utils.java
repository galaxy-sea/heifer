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

package plus.wcj.heifer.common.http;

import plus.wcj.heifer.common.http.cache.control.HttpCacheControl;

import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.http.CacheControl;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.StringJoiner;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/5/16
 */
public class Utils {

    private static final SpelExpressionParser PARSER = new SpelExpressionParser();
    private static final DefaultParameterNameDiscoverer PARAMETER_NAME_DISCOVERER = new DefaultParameterNameDiscoverer();

    public static String parser(String spelExpression, Method method, Object[] arguments) {
        String[] parameterNames = PARAMETER_NAME_DISCOVERER.getParameterNames(method);
        EvaluationContext context = new StandardEvaluationContext();
        if (parameterNames != null && parameterNames.length > 0) {
            for (int i = 0; i < parameterNames.length; i++) {
                context.setVariable(parameterNames[i], arguments[i]);
            }
        }
        return PARSER.parseExpression(spelExpression).getValue(context).toString();
    }

    public static String toHeaderValue(HttpCacheControl cacheControl) {
        StringJoiner stringJoiner = new StringJoiner(", ");
        if (cacheControl.maxAge() != 0) {
            stringJoiner.add("max-age=" + cacheControl.maxAge());
        }
        if (cacheControl.noCache()) {
            stringJoiner.add("no-cache");
        }
        if (cacheControl.noStore()) {
            stringJoiner.add("no-store");
        }
        if (cacheControl.mustRevalidate()) {
            stringJoiner.add("must-revalidate");
        }
        if (cacheControl.noTransform()) {
            stringJoiner.add("no-transform");
        }
        if (cacheControl.cachePublic()) {
            stringJoiner.add("public");
        }
        if (cacheControl.cachePrivate()) {
            stringJoiner.add("private");
        }
        if (cacheControl.proxyRevalidate()) {
            stringJoiner.add("proxy-revalidate");
        }
        if (cacheControl.sMaxAge() != 0) {
            stringJoiner.add("s-maxage=" + cacheControl.sMaxAge());
        }
        if (cacheControl.staleIfError() != 0) {
            stringJoiner.add("stale-if-error=" + cacheControl.staleIfError());
        }
        if (cacheControl.staleWhileRevalidate() != 0) {
            stringJoiner.add("stale-while-revalidate=" + cacheControl.staleWhileRevalidate());
        }
        String cacheControlValue = stringJoiner.toString();

        return StringUtils.hasLength(cacheControlValue) ? cacheControlValue : cacheControl.value();
    }


}
