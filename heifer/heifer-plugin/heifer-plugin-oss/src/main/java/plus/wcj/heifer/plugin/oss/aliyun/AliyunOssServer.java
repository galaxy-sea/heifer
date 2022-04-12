package plus.wcj.heifer.plugin.oss.aliyun;


import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PolicyConditions;
import plus.wcj.heifer.plugin.oss.OssServer;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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
public class AliyunOssServer implements OssServer {

    private final Map<String, OSS> aliyunOssMap;
    private final Map<String, AliyunOssProperties> aliyunOssPropertiesMap;


    @Override
    public Map<String, String> policy(String dir) {
        return this.policy(dir, OssServer.DEFAULT_OSS_KEY);
    }

    @Override
    public Map<String, String> policy(String dir, String ossKey) {
        AliyunOssProperties aliyunOssProperties = aliyunOssPropertiesMap.get(ossKey);
        OSS oss = this.aliyunOssMap.get(ossKey);


        long expireEndTime = System.currentTimeMillis() + aliyunOssProperties.getExpire();
        Date expiration = new Date(expireEndTime);
        // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
        PolicyConditions policyConditions = new PolicyConditions();
        policyConditions.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConditions.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

        String postPolicy = oss.generatePostPolicy(expiration, policyConditions);
        byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = oss.calculatePostSignature(postPolicy);

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

    @Override
    public URL redirect(String ossObjectPath) {
        return this.redirect(ossObjectPath, OssServer.DEFAULT_OSS_KEY);
    }

    @Override
    public URL redirect(String key, String ossKey) {
        AliyunOssProperties aliyunOssProperties = aliyunOssPropertiesMap.get(ossKey);
        OSS oss = this.aliyunOssMap.get(ossKey);

        long expireEndTime = System.currentTimeMillis() + aliyunOssProperties.getExpire();
        Date expiration = new Date(expireEndTime);
        return oss.generatePresignedUrl(aliyunOssProperties.getBucket(), key, expiration, HttpMethod.GET);
    }

    @Override
    public String putObject(String ossObjectPath, InputStream input, ObjectMetadata metadata) {
        return this.putObject(OssServer.DEFAULT_OSS_KEY, ossObjectPath, input, metadata);
    }

    @Override
    public String putObject(String ossObjectPath, File file, ObjectMetadata metadata) {
        return this.putObject(OssServer.DEFAULT_OSS_KEY, ossObjectPath, file, metadata);
    }

    @Override
    public String putObject(String ossObjectPath, MultipartFile file, ObjectMetadata metadata) {
        return this.putObject(OssServer.DEFAULT_OSS_KEY, OssServer.DEFAULT_OSS_KEY, file, metadata);
    }

    @Override
    public String putObject(String ossObjectPath, byte[] bytes, ObjectMetadata metadata) {
        return this.putObject(OssServer.DEFAULT_OSS_KEY, ossObjectPath, bytes, metadata);
    }

    @Override
    public String putObject(String ossKey, String ossObjectPath, MultipartFile file, ObjectMetadata metadata) {
        try {
            return this.putObject(ossKey, ossObjectPath, file.getInputStream(), metadata);
        } catch (IOException e) {
            throw new OSSException("MultipartFile to InputStream Exception");
        }
    }

    @Override
    public String putObject(String ossKey, String ossObjectPath, byte[] bytes, ObjectMetadata metadata) {
        return this.putObject(ossKey, ossObjectPath, new ByteArrayInputStream(bytes), metadata);
    }

    @Override
    public String putObject(String ossKey, String ossObjectPath, InputStream input, ObjectMetadata metadata) {
        AliyunOssProperties aliyunOssProperties = aliyunOssPropertiesMap.get(ossKey);
        OSS oss = this.aliyunOssMap.get(ossKey);

        oss.putObject(aliyunOssProperties.getBucket(), ossObjectPath, input, metadata);
        return aliyunOssProperties.getHost() + ossObjectPath;
    }


    @Override
    public String putObject(String ossKey, String ossObjectPath, File file, ObjectMetadata metadata) {
        AliyunOssProperties aliyunOssProperties = aliyunOssPropertiesMap.get(ossKey);
        OSS oss = this.aliyunOssMap.get(ossKey);

        oss.putObject(aliyunOssProperties.getBucket(), ossObjectPath, file, metadata);
        return aliyunOssProperties.getHost() + ossObjectPath;
    }


}
