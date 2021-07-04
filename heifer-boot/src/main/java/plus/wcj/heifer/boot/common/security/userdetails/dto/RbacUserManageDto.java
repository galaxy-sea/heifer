package plus.wcj.heifer.boot.common.security.userdetails.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户是否拥有全部数据权限和功能权限
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("rbac_user_manage")
@ApiModel(value = "RbacUserManage对象", description = "用户是否拥有全部数据权限和功能权限")
public class RbacUserManageDto implements Serializable {

    private static final long serialVersionUID = 1L;


    /** 全部数据权限，T全部，F部分 */
    @ApiModelProperty(value = "全部数据权限，T全部，F部分")
    @TableField("is_all_power")
    private Boolean allPower;

    /** 全部功能权限，T全部，F部分 */
    @ApiModelProperty(value = "全部功能权限，T全部，F部分")
    @TableField("is_all_authority")
    private Boolean allAuthority;


}
