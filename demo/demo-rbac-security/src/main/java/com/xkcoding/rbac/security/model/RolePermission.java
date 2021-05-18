package com.xkcoding.rbac.security.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色权限关系表
 * </p>
 *
 * @author changjinwei
 * @since 2021-05-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sec_role_permission")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 角色主键 */
    @TableId(value = "role_id", type = IdType.ASSIGN_ID)
    private Long roleId;

    /** 权限主键 */
    @TableField("permission_id")
    private Long permissionId;


}
