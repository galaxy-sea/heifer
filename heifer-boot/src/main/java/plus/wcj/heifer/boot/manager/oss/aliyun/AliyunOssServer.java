package plus.wcj.heifer.boot.manager.oss.aliyun;

import com.aliyun.oss.OSS;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import plus.wcj.heifer.boot.manager.oss.OssServer;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author changjin wei(魏昌进)
 * @see <a href="https://help.aliyun.com/document_detail/31927.htm?spm=a2c4g.11186623.2.9.17487d9cnTKLr7#concept-qp2-g4y-5db">服务端签名直传并设置上传回调 </a>
 * @see <a href="https://help.aliyun.com/document_detail/91868.html?spm=a2c4g.11186623.6.1751.1b707a74R0LCkS">服务端签名直传并设置上传回调 > Java </a>
 * @since 2021/7/16
 */
@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(AliyunOssProperties.class)
public class AliyunOssServer implements OssServer<AliyunOssProperties> {

    private final OSS ossClient;
    private final AliyunOssProperties aliyunOssProperties;

    @Override
    public Map<String, String> policy(String dir) {
        return this.policy(dir, aliyunOssProperties);
    }

    @Override
    public Map<String, String> policy(String dir, AliyunOssProperties aliyunOssProperties) {
        long expireEndTime = System.currentTimeMillis() + aliyunOssProperties.getExpire();
        Date expiration = new Date(expireEndTime);
        // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
        PolicyConditions policyConditions = new PolicyConditions();
        policyConditions.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConditions.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

        String postPolicy = ossClient.generatePostPolicy(expiration, policyConditions);
        byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = ossClient.calculatePostSignature(postPolicy);

        Map<String, String> respMap = new LinkedHashMap<>();
        //noinspection SpellCheckingInspection
        respMap.put("accessid", aliyunOssProperties.getAccessId());
        respMap.put("policy", encodedPolicy);
        respMap.put("signature", postSignature);
        respMap.put("dir", dir);
        respMap.put("host", aliyunOssProperties.getHost());
        respMap.put("expire", String.valueOf(expireEndTime));
        return respMap;
    }
}
