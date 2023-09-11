package plus.wcj.heifer.plugin.iam.auto.init;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author changjin wei(魏昌进)
 * @since 2023/9/10
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Permission {


    enum Type {

        /** 菜单 */
        menu,

        /** 按钮 */
        button,

        /** 外链 */
        url,
        ;
    }
}
