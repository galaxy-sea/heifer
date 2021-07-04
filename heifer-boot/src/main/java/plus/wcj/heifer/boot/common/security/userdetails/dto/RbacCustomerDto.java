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
 * 顾客信息
 * </p>
 *
 * @author changjin wei(魏昌进)
 * @since 2021-07-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("rbac_customer")
@ApiModel(value = "RbacCustomer对象", description = "顾客信息")
public class RbacCustomerDto implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 用户id
     *
     * @deprecated 未来要删除，
     */
    @Deprecated
    @ApiModelProperty(value = "用户id")
    @TableField("rbac_user_id")
    private Long rbacUserId;

}
