package plus.wcj.heifer.boot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.wcj.heifer.boot.manager.oss.OssServer;
import plus.wcj.heifer.boot.manager.oss.aliyun.AliyunOssProperties;

import java.util.Map;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/7/20
 */
@RestController
@RequestMapping("oss")
@RequiredArgsConstructor
public class OssController {
    private final OssServer<AliyunOssProperties> ossServer;

    @GetMapping("policy")
    public Map<String, String> policy() {
        return ossServer.policy("test/");
    }

}
