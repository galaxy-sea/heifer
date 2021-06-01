package plus.wcj.heifer.boot.common.validated;

import org.springframework.core.annotation.AliasFor;
import plus.wcj.heifer.boot.common.validated.dto.Valid;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/6/1
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Validated {

    @AliasFor(annotation = org.springframework.validation.annotation.Validated.class)
    Class<? extends Valid>[] value() default {};
}
