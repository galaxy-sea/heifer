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

package plus.wcj.heifer.common.mybatis.plus.example;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/19
 */
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectGradeEnumTest() {
        List<User> users = userMapper.selectList(null);
        Assert.isTrue(!CollectionUtils.isEmpty(users));
        for (User user : users) {
            GradeEnum grade = user.getGrade();
            Assert.isTrue(grade != null);
        }
    }


    @Test
    public void insterGradeEnumTest() {
        User user = new User();
        user.setId(0L);
        user.setName("xiaowei");
        user.setAge(0);
        user.setEmail("a@qq.com");
        user.setGrade(GradeEnum.SECONDORY);
        userMapper.insert(user);

        user = userMapper.selectEnum(user);
        Assert.isTrue(user != null);
        Assert.isTrue(user.getGrade() == GradeEnum.SECONDORY);
        user = userMapper.selectTest();
        Assert.isTrue(user != null);
        Assert.isTrue(user.getGrade() == GradeEnum.SECONDORY);

    }


}