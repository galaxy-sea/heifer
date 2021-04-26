package plus.wcj.heifer.boot.common.mvc.restful;

import lombok.Getter;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;

/**
 * @author changjin wei(魏昌进)
 * @date 2021/4/26
 */
public class ApiVersionCondition implements RequestCondition<ApiVersionCondition> {

    @Getter
    private int apiVersion;

    public ApiVersionCondition(int apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public ApiVersionCondition combine(ApiVersionCondition other) {
        return new ApiVersionCondition(other.getApiVersion());
    }

    @Override
    public ApiVersionCondition getMatchingCondition(HttpServletRequest request) {
        String version = request.getHeader("version");
        return version == null ? new ApiVersionCondition(0) : new ApiVersionCondition(Integer.parseInt(version));
    }

    @Override
    public int compareTo(ApiVersionCondition other, HttpServletRequest request) {
        return other.getApiVersion() - this.apiVersion;
    }
}
