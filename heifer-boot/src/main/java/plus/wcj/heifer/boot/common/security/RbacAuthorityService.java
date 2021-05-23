package plus.wcj.heifer.boot.common.security;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import plus.wcj.heifer.boot.common.exception.ResultException;
import plus.wcj.heifer.boot.common.exception.ResultStatus;
import plus.wcj.heifer.boot.common.security.userdetails.Permission;
import plus.wcj.heifer.boot.common.security.userdetails.Role;
import plus.wcj.heifer.boot.common.security.userdetails.UserPrincipal;
import plus.wcj.heifer.boot.common.security.userdetails.dao.CustomUserDetailsDao;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 动态路由认证
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2018-12-10 17:17
 */
@Deprecated
@Component
public class RbacAuthorityService {

    @Autowired
    private CustomUserDetailsDao customUserDetailsDao;

    @Autowired
    private RequestMappingHandlerMapping mapping;

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        checkRequest(request);

        Object userInfo = authentication.getPrincipal();
        boolean hasPermission = false;

        if (userInfo instanceof UserDetails) {
            UserPrincipal principal = (UserPrincipal) userInfo;
            Long userId = principal.getId();

            List<Role> roles = customUserDetailsDao.selectRoleByUserId(userId);
            List<Long> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
            List<Permission> permissions = customUserDetailsDao.selectPermissionByRoleIdList(roleIds);

            //获取资源，前后端分离，所以过滤页面权限，只保留按钮权限
            List<Permission> btnPerms = permissions.stream()
                                                   // 过滤页面权限
                                                   // TODO: 2021/5/19 changjin wei(魏昌进)  2是按钮
                                                   .filter(permission -> Objects.equals(permission.getType(), 2))
                                                   // 过滤 URL 为空
                                                   .filter(permission -> StringUtils.isNotBlank(permission.getUrl()))
                                                   // 过滤 METHOD 为空
                                                   .filter(permission -> StringUtils.isNotBlank(permission.getMethod())).collect(Collectors.toList());

            for (Permission btnPerm : btnPerms) {
                AntPathRequestMatcher antPathMatcher = new AntPathRequestMatcher(btnPerm.getUrl(), btnPerm.getMethod());
                if (antPathMatcher.matches(request)) {
                    hasPermission = true;
                    break;
                }
            }

            return hasPermission;
        } else {
            return false;
        }
    }

    /**
     * 校验请求是否存在
     *
     * @param request 请求
     */
    private void checkRequest(HttpServletRequest request) {
        // 获取当前 request 的方法
        String currentMethod = request.getMethod();
        Map<String, Set<String>> urlMapping = allUrlMapping();

        for (String uri : urlMapping.keySet()) {
            // 通过 AntPathRequestMatcher 匹配 url
            // 可以通过 2 种方式创建 AntPathRequestMatcher
            // 1：new AntPathRequestMatcher(uri,method) 这种方式可以直接判断方法是否匹配，因为这里我们把 方法不匹配 自定义抛出，所以，我们使用第2种方式创建
            // 2：new AntPathRequestMatcher(uri) 这种方式不校验请求方法，只校验请求路径
            AntPathRequestMatcher antPathMatcher = new AntPathRequestMatcher(uri);
            if (antPathMatcher.matches(request)) {
                if (!urlMapping.get(uri).contains(currentMethod)) {
                    throw new ResultException(ResultStatus.INTERNAL_SERVER_ERROR);
                } else {
                    return;
                }
            }
        }
        throw new ResultException(ResultStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 获取 所有URL Mapping，返回格式为{"/test":["GET","POST"],"/sys":["GET","DELETE"]}
     *
     * @return {@link Map<String, Set<String>>} 格式的 URL Mapping
     */
    private Map<String, Set<String>> allUrlMapping() {
        Map<String, Set<String>> urlMapping = new HashMap<>();

        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = mapping.getHandlerMethods();

        handlerMethods.forEach((k, v) -> {
            // 获取当前 key 下的获取所有URL
            Set<String> url = k.getPatternsCondition().getPatterns();
            RequestMethodsRequestCondition method = k.getMethodsCondition();

            // 为每个URL添加所有的请求方法
            url.forEach(s -> {
                urlMapping.put(s, method.getMethods().stream().map(Enum::toString).collect(Collectors.toSet()));

            });
        });

        return urlMapping;
    }
}
