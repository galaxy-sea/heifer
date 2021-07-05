package plus.wcj.heifer.boot.common.security.config.bean;

import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/6/29
 */
public class MethodSecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private final MethodSecurityExpressionOperations methodSecurityExpressionOperations;

    public MethodSecurityExpressionRoot(MethodSecurityExpressionOperations tMethodSecurityExpressionOperations) {
        this.methodSecurityExpressionOperations = tMethodSecurityExpressionOperations;
    }

    @Override
    public void setFilterObject(Object filterObject) {
        methodSecurityExpressionOperations.setFilterObject(filterObject);
    }

    @Override
    public Object getFilterObject() {
        return methodSecurityExpressionOperations.getFilterObject();
    }

    @Override
    public void setReturnObject(Object returnObject) {
        methodSecurityExpressionOperations.setReturnObject(returnObject);
    }

    @Override
    public Object getReturnObject() {
        return methodSecurityExpressionOperations.getReturnObject();
    }

    @Override
    public Object getThis() {
        return methodSecurityExpressionOperations.getThis();
    }

    @Override
    public Authentication getAuthentication() {
        return methodSecurityExpressionOperations.getAuthentication();
    }

    @Override
    public boolean hasAuthority(String authority) {
        System.out.println("toto 权限表达式，增加新功能");
        // TODO: 2021/6/30 changjin wei(魏昌进) 权限表达式，增加新功能
        return methodSecurityExpressionOperations.hasAuthority(authority);
    }

    @Override
    public boolean hasAnyAuthority(String... authorities) {
        return methodSecurityExpressionOperations.hasAnyAuthority(authorities);
    }

    @Override
    public boolean hasRole(String role) {
        return methodSecurityExpressionOperations.hasRole(role);
    }

    @Override
    public boolean hasAnyRole(String... roles) {
        return methodSecurityExpressionOperations.hasAnyRole(roles);
    }

    @Override
    public boolean permitAll() {
        return methodSecurityExpressionOperations.permitAll();
    }

    @Override
    public boolean denyAll() {
        return methodSecurityExpressionOperations.denyAll();
    }

    @Override
    public boolean isAnonymous() {
        return methodSecurityExpressionOperations.isAnonymous();
    }

    @Override
    public boolean isAuthenticated() {
        return methodSecurityExpressionOperations.isAuthenticated();
    }

    @Override
    public boolean isRememberMe() {
        return methodSecurityExpressionOperations.isRememberMe();
    }

    @Override
    public boolean isFullyAuthenticated() {
        return methodSecurityExpressionOperations.isFullyAuthenticated();
    }

    @Override
    public boolean hasPermission(Object target, Object permission) {
        return methodSecurityExpressionOperations.hasPermission(target, permission);
    }

    @Override
    public boolean hasPermission(Object targetId, String targetType, Object permission) {
        return methodSecurityExpressionOperations.hasPermission(targetId, targetType, permission);
    }
}
