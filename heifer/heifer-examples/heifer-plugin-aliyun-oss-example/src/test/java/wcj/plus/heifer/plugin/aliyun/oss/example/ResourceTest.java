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

package wcj.plus.heifer.plugin.aliyun.oss.example;

import com.aliyun.oss.common.utils.IOUtils;
import org.junit.jupiter.api.Test;
import plus.wcj.heifer.plugin.aliyun.oss.AliyunOssServer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/22
 */
@SuppressWarnings("deprecation")
@SpringBootTest
public class ResourceTest {

    @Autowired
    private AliyunOssServer aliyunOssServer;

    @Value("oss://beautiful-senior/sister1.jpg")
    private Resource defaultFile;

    @Test
    public void data() {
        aliyunOssServer.policy("baidu", "beautiful-senior");
    }

    @Test
    public void defaultTest() throws IOException {
        InputStream inputStream = defaultFile.getInputStream();
        byte[] bytes = IOUtils.readStreamAsByteArray(inputStream);
        Assert.isTrue(bytes != null);
        Assert.isTrue(bytes.length == 9440290);
    }


    @Value("oss://heifer-oss-private1/hello.JPG")
    private Resource privateFile;


    @Test
    public void privateTest() throws IOException {
        InputStream inputStream = privateFile.getInputStream();
        byte[] bytes = IOUtils.readStreamAsByteArray(inputStream);
        Assert.isTrue(bytes != null);
        Assert.isTrue(bytes.length == 192510);
    }
}
