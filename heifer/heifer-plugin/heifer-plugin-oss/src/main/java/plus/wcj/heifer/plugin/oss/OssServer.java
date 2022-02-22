package plus.wcj.heifer.plugin.oss;

import java.net.URL;
import java.util.Map;

/**
 * 通用的oss顶级接口
 *
 * @author changjinwei
 * @since 2021/7/19
 */

public interface OssServer<OP extends OssProperties> {

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
     * @param ossProperties 配置信息
     *
     * @return 授权信息
     */
    Map<String, String> policy(String dir, OP ossProperties);

    /**
     * 私有oss授权访问，
     *
     * @param key oss object path
     *
     * @return aliyun oss url
     */
    URL redirect(String key);

    /**
     * 私有oss授权访问，使用302重定向到aliyun oss
     *
     * @param key oss object path
     * @param ossProperties 配置信息
     *
     * @return aliyun oss url
     */
    URL redirect(String key, OP ossProperties);

}
