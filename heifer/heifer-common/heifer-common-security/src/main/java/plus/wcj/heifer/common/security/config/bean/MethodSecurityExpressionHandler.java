package plus.wcj.heifer.common.security.config.bean;// package plus.wcj.heifer.boot.common.security.config.bean;
//
// import org.aopalliance.intercept.MethodInvocation;
// import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
// import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
// import org.springframework.security.core.Authentication;
//
// /**
//  * @author changjin wei(魏昌进)
//  * @since 2021/6/29
//  */
// public class MethodSecurityExpressionHandler extends DefaultMethodSecurityExpressionHandler {
//
//     @Override
//     public MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {
//
//         MethodSecurityExpressionOperations securityExpressionRoot = super.createSecurityExpressionRoot(authentication, invocation);
//
//         return new MethodSecurityExpressionRoot(securityExpressionRoot);
//     }
// }
