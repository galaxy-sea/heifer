package plus.wcj.heifer.boot.common.security.userdetails;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 权限表
 * </p>
 *
 * @author changjinwei
 * @since 2021-05-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sec_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 权限名 */
    @TableField("name")
    private String name;

    /** 类型为页面时，代表前端路由地址，类型为按钮时，代表后端接口地址 */
    @TableField("url")
    private String url;

    /** 权限类型，页面-1，按钮-2 */
    @TableField("type")
    private Integer type;

    /** 权限表达式 */
    @TableField("permission")
    private String permission;

    /** 后端接口访问方式 */
    @TableField("method")
    private String method;

    /** 排序 */
    @TableField("sort")
    private Integer sort;

    /** 父级id */
    @TableField("parent_id")
    private Long parentId;
}
