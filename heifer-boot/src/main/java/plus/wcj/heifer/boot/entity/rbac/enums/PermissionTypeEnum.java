package plus.wcj.heifer.boot.entity.rbac.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author changjin wei(魏昌进)
 * @since 2021/6/23
 */
@Getter
@AllArgsConstructor
public enum PermissionTypeEnum {
    /** 客户端 */
    CLIENT("客户端", 1),
    /** 菜单 */
    MENU("菜单", 2),
    /** 按钮 */
    BUTTON("按钮", 3),
    ;
    private final String name;
    private final Integer value;


}
