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

package plus.wcj.heifer.common.mybatisplus.validation;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import plus.wcj.heifer.metadata.exception.ResultException;

import org.springframework.core.MethodParameter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/8/11
 */
public class OrderByValidator implements ConstraintValidator<OrderByValid, IPage<?>> {

    private Set<String> field;

    @Override
    public boolean isValid(IPage<?> value, ConstraintValidatorContext context) {
        if (value == null || CollectionUtils.isEmpty(value.orders()) || field.isEmpty()) {
            return true;
        }

        List<OrderItem> orders = value.orders();
        for (OrderItem order : orders) {
            if (!field.contains(order.getColumn())) {
                context.disableDefaultConstraintViolation();
                context
                        .buildConstraintViolationWithTemplate("User  already exists!")
                        .addConstraintViolation();


                return false;
            }
        }
        return true;
    }

    @Override
    public void initialize(OrderByValid constraintAnnotation) {
        if (constraintAnnotation.field() != null && constraintAnnotation.field().length != 0) {
            this.field = Set.of(constraintAnnotation.field());
        } else {
            Class<?> orderClass = this.getOrderClass();
            this.field = this.toField(orderClass);
        }

        List.of(constraintAnnotation.excludeField()).forEach(this.field::remove);
    }

    private Set<String> toField(Class<?> orderClass) {
        return orderClass == null ? Set.of() :
                TableInfoHelper.getAllFields(orderClass).stream().map(Field::getName).collect(Collectors.toSet());
    }


    private Class<?> getOrderClass() {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        HandlerMethod handlerMethod = (HandlerMethod) request.getAttribute(HandlerMapping.BEST_MATCHING_HANDLER_ATTRIBUTE);

        for (MethodParameter methodParameter : handlerMethod.getMethodParameters()) {
            ParameterizedType genericParameterType = (ParameterizedType) methodParameter.getGenericParameterType();
            if (IPage.class.isAssignableFrom((Class<?>) genericParameterType.getRawType())) {
                Type[] actualTypeArguments = genericParameterType.getActualTypeArguments();
                if (actualTypeArguments != null && actualTypeArguments.length != 0) {
                    Type actualTypeArgument = actualTypeArguments[0];
                    return (Class<?>) actualTypeArgument;
                }
            }
        }
        return null;
    }
}
