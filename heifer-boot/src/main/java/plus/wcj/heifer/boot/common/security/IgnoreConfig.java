package plus.wcj.heifer.boot.common.security;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 忽略配置
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-17 17:37
 */
@Data
public class IgnoreConfig {
    /**
     * 需要忽略的 URL 格式，不考虑请求方法
     */
    private List<String> pattern = new ArrayList<String>();

    /**
     * 需要忽略的 GET 请求
     */
    private List<String> get = new ArrayList<String>();

    /**
     * 需要忽略的 POST 请求
     */
    private List<String> post = new ArrayList<String>();

    /**
     * 需要忽略的 DELETE 请求
     */
    private List<String> delete = new ArrayList<String>();

    /**
     * 需要忽略的 PUT 请求
     */
    private List<String> put = new ArrayList<String>();

    /**
     * 需要忽略的 HEAD 请求
     */
    private List<String> head = new ArrayList<String>();

    /**
     * 需要忽略的 PATCH 请求
     */
    private List<String> patch = new ArrayList<String>();

    /**
     * 需要忽略的 OPTIONS 请求
     */
    private List<String> options = new ArrayList<String>();

    /**
     * 需要忽略的 TRACE 请求
     */
    private List<String> trace = new ArrayList<String>();
}
