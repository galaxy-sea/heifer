package plus.wcj.heifer.plugin.rbac.pojo.entity.tenant;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import plus.wcj.heifer.metadata.annotation.PostValid;
import plus.wcj.heifer.metadata.annotation.PutValid;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.springframework.data.redis.core.RedisHash;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 部门
 * </p>
 *
 * @author changjinwei
 * @since 2021-11-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@RedisHash("RbacDept")
@TableName("rbac_dept")
@ApiModel(value = "RbacDept对象", description = "部门")
public class RbacDept implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键id */
    @ApiModelProperty(value = "主键id")
    @NotNull(groups = {PostValid.class, PutValid.class}, message = "id is null")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 父节点名称 */
    @ApiModelProperty(value = "父节点名称")
    @NotNull(groups = {PostValid.class}, message = "parentId is null")
    @TableField("parent_id")
    private Long parentId;

    /** 租户id */
    @ApiModelProperty(value = "租户id")
    @NotNull(groups = {PostValid.class}, message = "rbacTenantId is null")
    @TableField("rbac_tenant_id")
    private Long rbacTenantId;

    /** 部门名称 */
    @ApiModelProperty(value = "部门名称")
    @NotNull(groups = {PostValid.class}, message = "name is null")
    @TableField("name")
    private String name;

    @TableField("create_time")
    private Date createTime;

    @TableField("create_by")
    private Long createBy;

    @TableField("update_time")
    private Date updateTime;

    @TableField("update_by")
    private Long updateBy;


}
