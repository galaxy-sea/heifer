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

package plus.wcj.heifer.common.mybatisplus.mvc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import plus.wcj.heifer.metadata.Constant;
import plus.wcj.heifer.metadata.exception.ResultException;
import plus.wcj.heifer.metadata.exception.ResultStatusEnum;

import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.LinkedList;
import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/8/9
 */
public class PageMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return IPage.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        long current = this.getLong(webRequest, Constant.CURRENT);
        long size = this.getLong(webRequest, Constant.SIZE);
        List<OrderItem> orderItem = this.geOrders(webRequest);

        PageDTO<?> pageDTO = new PageDTO<>(current, size);
        pageDTO.setOrders(orderItem);

        return pageDTO;
    }

    private List<OrderItem> geOrders(NativeWebRequest webRequest) {

        List<OrderItem> orderItemList = new LinkedList<>();

        for (int i = 0; ; i++) {
            String column = webRequest.getParameter(Constant.ORDERS + "[" + i + "].column");
            String asc = webRequest.getParameter(Constant.ORDERS + "[" + i + "].asc");
            if (!StringUtils.hasText(column)) {
                return orderItemList;
            }
            orderItemList.add(new OrderItem(column, this.isAsc(asc)));
        }
    }


    private boolean isAsc(String asc) {
        return !StringUtils.hasText(asc) || "true".equals(asc);
    }


    private long getLong(NativeWebRequest webRequest, String name) {
        String parameter = this.getValue(webRequest, name);
        if (StringUtils.hasText(parameter)) {
            return Long.parseLong(parameter);
        }
        throw new ResultException(ResultStatusEnum.BAD_REQUEST);
    }

    private String getValue(NativeWebRequest webRequest, String name) {
        String parameter = webRequest.getParameter(name);
        if (!StringUtils.hasText(parameter)) {
            parameter = webRequest.getHeader(name);
        }
        return parameter;
    }
}

