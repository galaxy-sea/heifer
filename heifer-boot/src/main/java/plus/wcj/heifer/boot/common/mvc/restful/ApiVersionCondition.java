package plus.wcj.heifer.boot.common.mvc.restful;

import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;

/**
 * @author changjin wei(魏昌进)
 * @date 2021/4/29
 */
public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {

    private int apiVersion;

    public ApiVersionCondition(int apiVersion) {
        this.apiVersion = apiVersion;
    }


    @Override
    public ApiVersionCondition combine(ApiVersionCondition other) {
        // 采用最后定义优先原则，则方法上的定义覆盖类上面的定义
        return new ApiVersionCondition(other.getApiVersion());
    }


    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest request) {

        int version = Integer.parseInt(request.getHeader("version"));

        // 如果请求的版本号大于配置版本号， 则满足
        if (version == this.apiVersion) {
            return this;
        }
        return null;
    }


    @Override
    public int compareTo(ApiVersionCondition other, HttpServletRequest request) {
        // 优先匹配最新的版本号
        return other.getApiVersion() - this.apiVersion;
    }

    public int getApiVersion() {
        return apiVersion;
    }
}
