/*
 * Copyright 2021-2023 the original author or authors.
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

package plus.wcj.heifer.plugin.iam.auth.permission;

import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取功能权限
 *
 * @author changjin wei(魏昌进)
 * @since 2023/4/5
 */
@Component
public class AbacPermissionCustomizer implements PermissionCustomizer {

    private final List<AbacAttribute> abacAttributes;

    private final StandardEvaluationContext context;

    public AbacPermissionCustomizer(List<AbacAttribute> abacAttributes) {
        this.abacAttributes = abacAttributes;
        this.context = new StandardEvaluationContext();
        this.context.addPropertyAccessor(new MapAccessor());
    }

    public List<String> customize(Long accountId, Long tenantId) {

        List<String> listPermission = new ArrayList<>();

        for (AbacAttribute abacAttribute : abacAttributes) {
            AbacAttribute.Attribute attributes = abacAttribute.getAttributes(accountId, tenantId);
            List<AbacAttribute.AbacExpression> abacExpressions = abacAttribute.listAbacExpression(accountId, tenantId);
            for (AbacAttribute.AbacExpression abacExpression : abacExpressions) {
                ExpressionParser parser = new SpelExpressionParser();
                try {
                    Boolean flag = parser.parseExpression(abacExpression.getExpression())
                            .getValue(context, attributes, Boolean.class);
                    if (flag) {
                        List<String> strings = abacAttribute.listPermission(accountId, tenantId, abacExpression);
                        listPermission.addAll(strings);
                    }
                } catch (Exception ignored) {
                }
            }
        }
        return listPermission;
    }

}
