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

package plus.wcj.heifer.common.apisix.examples;

import plus.wck.heifer.api.User;
import plus.wck.heifer.api.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/5/11
 */
@RestController
@RequestMapping
public class HelloController {

    @Autowired
    private UserService userService;


    @GetMapping("hello")
    public Map<String,Object> hello(){

        User user = userService.getUser(1);

        return new HashMap<String, Object>() {{
            put("hello", "wodesijie");
            put("user", user);
        }};
    }

}
