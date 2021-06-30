package plus.wcj.heifer.boot.common.security.userdetails.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author changjinwei
 * @since 2021-05-23
 */
@Data
@Accessors(chain = true)
@TableName("rbac_role")
@ApiModel(value = "RbacRoleDo对象", description = "角色表")
public class RbacRoleDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 租户id */
    @ApiModelProperty(value = "租户id")
    @TableField("rbac_org_id")
    private Long rbacOrgId;

    /** 名称 */
    @ApiModelProperty(value = "名称")
    @TableField("name")
    private String name;


}
