// package plus.wcj.heifer.boot.common.security.config.bean;
//
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.GrantedAuthority;
//
// import java.util.Collection;
//
// /**
//  * @author changjinwei
//  * @since 2021/7/4
//  */
// public class HeiferUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {
//
//     /** 登录类型 */
//     private final loginTypeEnum loginType;
//
//     /** 登录租户 */
//     private final Long orgId;
//
//
//     public HeiferUsernamePasswordAuthenticationToken(Object principal, Object credentials, loginTypeEnum loginType, Long orgId) {
//         super(principal, credentials);
//         this.loginType = loginType;
//         this.orgId = orgId;
//     }
//
//     public HeiferUsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities, loginTypeEnum loginType, Long orgId) {
//         super(principal, credentials, authorities);
//         this.loginType = loginType;
//         this.orgId = orgId;
//     }
//
//     public loginTypeEnum getLoginType() {
//         return loginType;
//     }
//
//     public Long getOrgId() {
//         return orgId;
//     }
//
//     /**
//      * 登录方式
//      */
//     public enum loginTypeEnum {
//
//         /** 用户名+登录 */
//         USERNAME_PASSWORD,
//         /** 邮箱+密码 */
//         EMAIL_PASSWORD,
//         /** 手机+密码 */
//         PHONE_PASSWORD,
//
//         /** 手机+验证码 */
//         PHONE_CODE,
//         /** 邮箱+验证码 */
//         EMAIL_CODE,
//     }
//
// }
