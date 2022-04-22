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
