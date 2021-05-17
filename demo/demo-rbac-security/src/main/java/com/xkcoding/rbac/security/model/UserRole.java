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
 * 用户角色关系表
 * </p>
 *
 * @author changjinwei
 * @since 2021-05-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sec_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 用户主键 */
    @TableId(value = "user_id", type = IdType.ASSIGN_ID)
    private Long userId;

    /** 角色主键 */
    @TableField("role_id")
    private Long roleId;


}
