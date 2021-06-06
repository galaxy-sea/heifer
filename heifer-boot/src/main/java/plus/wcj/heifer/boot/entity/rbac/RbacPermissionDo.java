package plus.wcj.heifer.boot.entity.rbac;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import plus.wcj.heifer.boot.extension.validator.PostValid;
import plus.wcj.heifer.boot.extension.validator.PutValid;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 功能权限
 * </p>
 *
 * @author changjinwei
 * @since 2021-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("rbac_permission")
@ApiModel(value = "RbacPermissionDo对象", description = "功能权限")
public class RbacPermissionDo implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @ApiModelProperty(value = "主键ID")
    @NotNull(groups = {PostValid.class, PutValid.class}, message = "id is null")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 父节点名称 */
    @ApiModelProperty(value = "父节点名称")
    @NotNull(groups = {PostValid.class}, message = "parentId is null")
    @TableField("parent_id")
    private Long parentId;

    /** 客户端id */
    @ApiModelProperty(value = "客户端id")
    @NotNull(groups = {PostValid.class}, message = "tenantClientId is null")
    @TableField("tenant_client_id")
    private Long tenantClientId;

    /** 权限名称 */
    @ApiModelProperty(value = "权限名称")
    @NotNull(groups = {PostValid.class}, message = "name is null")
    @TableField("name")
    private String name;

    /** 权限表达式，用:分割 */
    @ApiModelProperty(value = "权限表达式，用:分割")
    @NotNull(groups = {PostValid.class}, message = "permission is null")
    @TableField("permission")
    private String permission;

    /** 1：菜单，2：按钮 */
    @ApiModelProperty(value = "1：菜单，2：按钮")
    @NotNull(groups = {PostValid.class}, message = "type is null")
    @TableField("type")
    private Integer type;

    /** 排序，默认asc */
    @ApiModelProperty(value = "排序，默认asc")
    @NotNull(groups = {PostValid.class}, message = "sort is null")
    @TableField("sort")
    private Integer sort;

    @NotNull(groups = {PostValid.class}, message = "createBy is null")
    @TableField("create_by")
    private Long createBy;

    @NotNull(groups = {PostValid.class}, message = "updateBy is null")
    @TableField("update_by")
    private Long updateBy;

    @NotNull(groups = {PostValid.class}, message = "createTime is null")
    @TableField("create_time")
    private Date createTime;

    @NotNull(groups = {PostValid.class}, message = "updateTime is null")
    @TableField("update_time")
    private Date updateTime;


}
