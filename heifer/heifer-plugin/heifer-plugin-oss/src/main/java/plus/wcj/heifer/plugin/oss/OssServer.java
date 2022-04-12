package plus.wcj.heifer.plugin.oss;

import com.aliyun.oss.model.ObjectMetadata;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

/**
 * 通用的oss顶级接口
 *
 * @author changjinwei
 * @since 2021/7/19
 */

public interface OssServer {

    String DEFAULT_OSS_KEY = "default";


    /**
     * 基于Post Policy的使用规则在服务端通过各种语言代码完成签名，并且设置上传回调，然后通过表单直传数据到OSS
     *
     * @param dir 用户上传文件时指定的前缀。
     *
     * @return 授权信息
     */
    Map<String, String> policy(String dir);

    /**
     * 基于Post Policy的使用规则在服务端通过各种语言代码完成签名，并且设置上传回调，然后通过表单直传数据到OSS
     *
     * @param dir 用户上传文件时指定的前缀。
     * @param ossKey 配置信息
     *
     * @return 授权信息
     */
    Map<String, String> policy(String dir, String ossKey);

    /**
     * 私有oss授权访问，
     *
     * @param ossObjectPath oss object path
     *
     * @return aliyun oss url
     */
    URL redirect(String ossObjectPath);

    /**
     * 私有oss授权访问，使用302重定向到aliyun oss
     *
     * @param ossObjectPath oss object path
     * @param ossKey 配置信息
     *
     * @return aliyun oss url
     */
    URL redirect(String ossObjectPath, String ossKey);

    String putObject(String ossObjectPath, InputStream input, ObjectMetadata metadata);

    String putObject(String ossObjectPath, File file, ObjectMetadata metadata);

    String putObject(String ossObjectPath, MultipartFile file, ObjectMetadata metadata);

    String putObject(String ossObjectPath, byte[] bytes, ObjectMetadata metadata);

    String putObject(String ossKey, String ossObjectPath, MultipartFile file, ObjectMetadata metadata);

    String putObject(String ossKey, String ossObjectPath, byte[] bytes, ObjectMetadata metadata);

    String putObject(String ossKey, String ossObjectPath, InputStream input, ObjectMetadata metadata);

    String putObject(String ossKey, String ossObjectPath, File file, ObjectMetadata metadata);
}
