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
    public Object getFilterObject() {
        return this.methodSecurityExpressionOperations.getFilterObject();
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.methodSecurityExpressionOperations.setFilterObject(filterObject);
    }

    @Override
    public Object getReturnObject() {
        return this.methodSecurityExpressionOperations.getReturnObject();
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.methodSecurityExpressionOperations.setReturnObject(returnObject);
    }

    @Override
    public Object getThis() {
        return this.methodSecurityExpressionOperations.getThis();
    }

    @Override
    public Authentication getAuthentication() {
        return this.methodSecurityExpressionOperations.getAuthentication();
    }

    @Override
    public boolean hasAuthority(String authority) {
        System.out.println("toto 权限表达式，增加新功能");
        // TODO: 2021/6/30 changjin wei(魏昌进) 权限表达式，增加新功能
        return this.methodSecurityExpressionOperations.hasAuthority(authority);
    }

    @Override
    public boolean hasAnyAuthority(String... authorities) {
        return this.methodSecurityExpressionOperations.hasAnyAuthority(authorities);
    }

    @Override
    public boolean hasRole(String role) {
        return this.methodSecurityExpressionOperations.hasRole(role);
    }

    @Override
    public boolean hasAnyRole(String... roles) {
        return this.methodSecurityExpressionOperations.hasAnyRole(roles);
    }

    @Override
    public boolean permitAll() {
        return this.methodSecurityExpressionOperations.permitAll();
    }

    @Override
    public boolean denyAll() {
        return this.methodSecurityExpressionOperations.denyAll();
    }

    @Override
    public boolean isAnonymous() {
        return this.methodSecurityExpressionOperations.isAnonymous();
    }

    @Override
    public boolean isAuthenticated() {
        return this.methodSecurityExpressionOperations.isAuthenticated();
    }

    @Override
    public boolean isRememberMe() {
        return this.methodSecurityExpressionOperations.isRememberMe();
    }

    @Override
    public boolean isFullyAuthenticated() {
        return this.methodSecurityExpressionOperations.isFullyAuthenticated();
    }

    @Override
    public boolean hasPermission(Object target, Object permission) {
        return this.methodSecurityExpressionOperations.hasPermission(target, permission);
    }

    @Override
    public boolean hasPermission(Object targetId, String targetType, Object permission) {
        return this.methodSecurityExpressionOperations.hasPermission(targetId, targetType, permission);
    }
}
