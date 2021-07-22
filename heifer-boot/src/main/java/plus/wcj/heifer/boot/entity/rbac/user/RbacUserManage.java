package plus.wcj.heifer.boot.entity.rbac.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import plus.wcj.heifer.boot.extension.validator.PostValid;
import plus.wcj.heifer.boot.extension.validator.PutValid;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户是否拥有全部数据权限和功能权限
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@Data
@EqualsAndHashCode()
@Accessors(chain = true)
@TableName("rbac_user_manage")
@ApiModel(value = "RbacUserManage对象", description = "用户是否拥有全部数据权限和功能权限")
public class RbacUserManage implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键id */
    @ApiModelProperty(value = "主键id")
    @NotNull(groups = {PostValid.class, PutValid.class}, message = "id is null")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 用户id */
    @ApiModelProperty(value = "用户id")
    @NotNull(groups = {PostValid.class}, message = "rbacUserId is null")
    @TableField("rbac_user_id")
    private Long rbacUserId;

    /** 全部数据权限，T全部，F部分 */
    @ApiModelProperty(value = "全部数据权限，T全部，F部分")
    @NotNull(groups = {PostValid.class}, message = "allPower is null")
    @TableField("is_all_power")
    private Boolean allPower;

    /** 全部功能权限，T全部，F部分 */
    @ApiModelProperty(value = "全部功能权限，T全部，F部分")
    @NotNull(groups = {PostValid.class}, message = "allAuthority is null")
    @TableField("is_all_authority")
    private Boolean allAuthority;

    @TableField("create_time")
    private Date createTime;

    @NotNull(groups = {PostValid.class}, message = "createBy is null")
    @TableField("create_by")
    private Long createBy;

    @TableField("update_time")
    private Date updateTime;

    @TableField("update_by")
    private Long updateBy;


}
