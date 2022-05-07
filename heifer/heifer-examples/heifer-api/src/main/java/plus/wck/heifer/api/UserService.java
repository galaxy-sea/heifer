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

package plus.wck.heifer.api;

import plus.wcj.heifer.metadata.annotation.ResultResponseBody;
import plus.wcj.heifer.metadata.bean.Result;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/20
 */
public interface UserService {

    String path = "feign";

    /** 原生 ResponseBody返回 */
    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    User getUser(@PathVariable("id") long id);

    /** 自定义 ResultResponseBody 统一返回 */
    @RequestMapping(method = RequestMethod.GET, value = "/Resultv1")
    @ResultResponseBody
    User getResultv1();

    /** 硬编码 Result<User> 统一返回同时带上ResultResponseBody统一返回 */
    @RequestMapping(method = RequestMethod.GET, value = "/Resulv2")
    @ResultResponseBody
    Result<User> getResultv2();
}
