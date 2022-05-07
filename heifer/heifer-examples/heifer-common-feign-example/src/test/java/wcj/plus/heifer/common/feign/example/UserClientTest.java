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

package wcj.plus.heifer.common.feign.example;

import org.junit.jupiter.api.Test;
import plus.wcj.heifer.metadata.bean.Result;
import plus.wck.heifer.api.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/20
 */
@SpringBootTest
public class UserClientTest {

    @Autowired
    private UserClient userClient;


    @Test
    public void getUser() {
        User getUser = userClient.getUser(1);

        User user = new User();
        user.setName("xiaowei");
        user.setAge(13);

        Assert.isTrue(user.equals(getUser));

    }


    @Test
    public void getResultV1() {
        User getResult = userClient.getResultv1();

        User user = new User();
        user.setName("xiaowei");
        user.setAge(13);

        Assert.isTrue(user.equals(getResult));
    }


    @Test
    public void getResultV2() {
        Result<User> resultv2 = userClient.getResultv2();

        Assert.isTrue(resultv2 != null);

        User user = new User();
        user.setName("xiaowei");
        user.setAge(13);

        Assert.isTrue(user.equals(resultv2.getData()));
    }

}