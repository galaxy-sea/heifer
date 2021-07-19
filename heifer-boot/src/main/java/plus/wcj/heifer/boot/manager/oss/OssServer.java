package plus.wcj.heifer.boot.manager.oss;

import java.util.Map;

/**
 * 通用的oss顶级接口
 *
 * @author changjinwei
 * @since 2021/7/19
 */

public interface OssServer {

    /**
     * 基于Post Policy的使用规则在服务端通过各种语言代码完成签名，并且设置上传回调，然后通过表单直传数据到OSS
     *
     * @param dir 用户上传文件时指定的前缀。
     *
     * @return 授权信息
     */
    Map<String, String> policy(String dir);

}
