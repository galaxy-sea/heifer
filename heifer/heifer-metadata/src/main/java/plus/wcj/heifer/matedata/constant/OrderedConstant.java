package plus.wcj.heifer.matedata.constant;

import org.springframework.core.Ordered;

/**
 * @author changjin wei(魏昌进)
 * @since 2022/4/7
 */
public interface OrderedConstant {

    int HEIFER_BOOT = Ordered.HIGHEST_PRECEDENCE;

    int OPEN_FEIGN = Ordered.HIGHEST_PRECEDENCE + 1;

}
