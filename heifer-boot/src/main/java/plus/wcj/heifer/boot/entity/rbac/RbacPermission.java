package plus.wcj.heifer.boot.entity.rbac;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import plus.wcj.heifer.boot.extension.validator.PostValid;
import plus.wcj.heifer.boot.extension.validator.PutValid;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 功能权限
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("rbac_permission")
@ApiModel(value = "RbacPermission对象", description = "功能权限")
public class RbacPermission implements Serializable {

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

    /** client:客户端,menu:菜单,button:按钮 */
    @ApiModelProperty(value = "client:客户端,menu:菜单,button:按钮")
    @NotNull(groups = {PostValid.class}, message = "type is null")
    @TableField("type")
    private RbacPermission.TypeEnum type;

    /** 排序，默认asc */
    @ApiModelProperty(value = "排序，默认asc")
    @NotNull(groups = {PostValid.class}, message = "sort is null")
    @TableField("sort")
    private Integer sort;

    @TableField("create_time")
    private Date createTime;

    @NotNull(groups = {PostValid.class}, message = "createBy is null")
    @TableField("create_by")
    private Long createBy;

    @TableField("update_time")
    private Date updateTime;

    @TableField("update_by")
    private Long updateBy;


    @Getter
    @RequiredArgsConstructor
    public enum TypeEnum {
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

}
