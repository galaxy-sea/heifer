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

package plus.wcj.heifer.boot.examples;

import plus.wcj.heifer.metadata.bean.Result;
import plus.wcj.libby.http.cache.control.annotation.HttpCacheControl;
import plus.wck.heifer.api.User;
import plus.wck.heifer.api.UserService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/20
 */
@RestController
@RequestMapping(value = UserService.path)
public class UserController implements UserService {
    @Override
    @HttpCacheControl(key = "123", maxAge = 5, cachePublic = true)
    public User getUser(long id) {
        User user = new User();
        user.setName("xiaowei");
        user.setAge((int) (System.currentTimeMillis()%100000));

        return user;
    }

    @Override
    public User getResultv1() {
        User user = new User();
        user.setName("xiaowei");
        user.setAge(13);
        return user;
    }

    @Override
    public Result<User> getResultv2() {
        User user = new User();
        user.setName("xiaowei");
        user.setAge(13);
        return Result.success(user);
    }
}
