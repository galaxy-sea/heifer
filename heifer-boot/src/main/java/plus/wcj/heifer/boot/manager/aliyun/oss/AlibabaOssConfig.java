package plus.wcj.heifer.boot.manager.aliyun.oss;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;

import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @see <a href="https://help.aliyun.com/document_detail/31927.htm?spm=a2c4g.11186623.2.9.17487d9cnTKLr7#concept-qp2-g4y-5db">服务端签名直传并设置上传回调 </a>
 * @see <a href="https://help.aliyun.com/document_detail/91868.html?spm=a2c4g.11186623.6.1751.1b707a74R0LCkS">服务端签名直传并设置上传回调 > Java </a>
 *
 * @author changjin wei(魏昌进)
 * @since 2021/7/16
 *
 */
public class AlibabaOssConfig {

    public static void main(String[] args) throws ParseException, FileNotFoundException {
        String accessId = "LTAI5tFHbYfReKAUyNHEeZG7"; // 请填写您的AccessKeyId。
        String accessKey = "C9HtKXOPGhXGY3GIlnVnBMBDfHUIb2"; // 请填写您的AccessKeySecret。
        String endpoint = "oss-cn-hangzhou.aliyuncs.com"; // 请填写您的 endpoint。
        String bucket = "heifer.oss-cn-hangzhou.aliyuncs.com"; // 请填写您的 bucketname 。
        String host = "https://" + bucket + "." + endpoint; // host的格式为 bucketname.endpoint
        // callbackUrl为 上传回调服务器的URL，请将下面的IP和Port配置为您自己的真实信息。
        String callbackUrl = "http://88.88.88.88:8888";
        String dir = "user-dir-prefix/"; // 用户上传文件时指定的前缀。

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessId, accessKey);
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            // PostObject请求最大可支持的文件大小为5 GB，即CONTENT_LENGTH_RANGE为5*1024*1024*1024。
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = ossClient.calculatePostSignature(postPolicy);

            Map<String, String> respMap = new LinkedHashMap<String, String>();
            respMap.put("accessid", accessId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            // respMap.put("expire", formatISO8601Date(expiration));


            // JSONObject ja1 = JSONObject.fromObject(respMap);
            // // System.out.println(ja1.toString());
            // response.setHeader("Access-Control-Allow-Origin", "*");
            // response.setHeader("Access-Control-Allow-Methods", "GET, POST");
            // response(request, response, ja1.toString());

        } catch (Exception e) {
            // Assert.fail(e.getMessage());
            System.out.println(e.getMessage());
        } finally {
            ossClient.shutdown();
        }
    }

}
