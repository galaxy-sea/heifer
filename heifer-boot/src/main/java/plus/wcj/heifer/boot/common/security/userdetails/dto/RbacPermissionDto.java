package plus.wcj.heifer.boot.common.security.userdetails.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 功能权限
 * </p>
 *
 * @author changjinwei
 * @since 2021-05-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("rbac_permission")
@ApiModel(value="RbacPermissionDo对象", description="功能权限")
public class RbacPermissionDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 父节点名称 */
    @ApiModelProperty(value = "父节点名称")
    @TableField("parent_id")
    private Long parentId;

    /** 客户端id */
    @ApiModelProperty(value = "客户端id")
    @TableField("tenant_client_id")
    private Long tenantClientId;

    /** 权限名称 */
    @ApiModelProperty(value = "权限名称")
    @TableField("name")
    private String name;

    /** 权限表达式，用:分割 */
    @ApiModelProperty(value = "权限表达式，用:分割")
    @TableField("permission")
    private String permission;

    /** 1：菜单，2：按钮 */
    @ApiModelProperty(value = "1：菜单，2：按钮")
    @TableField("type")
    private Integer type;

    /** 排序，默认asc */
    @ApiModelProperty(value = "排序，默认asc")
    @TableField("sort")
    private Integer sort;


}
