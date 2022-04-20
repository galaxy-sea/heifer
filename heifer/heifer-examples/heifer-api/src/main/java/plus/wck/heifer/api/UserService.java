package plus.wck.heifer.api;

import plus.wcj.heifer.metadata.annotation.ResultResponseBody;
import plus.wcj.heifer.metadata.bean.Result;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/20
 */
public interface UserService {

    String path = "feign";

    /** 原生 ResponseBody返回 */
    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    User getUser(@PathVariable("id") long id);

    /** 自定义 ResultResponseBody 统一返回 */
    @RequestMapping(method = RequestMethod.GET, value = "/Resultv1")
    @ResultResponseBody
    User getResultv1();

    /** 硬编码 Result<User> 统一返回同时带上ResultResponseBody统一返回 */
    @RequestMapping(method = RequestMethod.GET, value = "/Resulv2")
    @ResultResponseBody
    Result<User> getResultv2();
}
