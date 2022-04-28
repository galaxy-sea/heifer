package plus.wcj.heifer.plugin.iam.entity;

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

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("iam_access")
@ApiModel(value = "IamAccess对象", description = "")
public class IamAccess implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @ApiModelProperty(value = "主键ID")
    @NotNull(groups = {PutValid.class}, message = "id is null")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 账户id */
    @ApiModelProperty(value = "账户id")
    @NotNull(groups = {PostValid.class}, message = "iamAccountManageId is null")
    @TableField("iam_account_manage_id")
    private Long iamAccountManageId;

    /** access_key_id */
    @ApiModelProperty(value = "access_key_id")
    @NotNull(groups = {PostValid.class}, message = "accessKeyId is null")
    @TableField("access_key_id")
    private String accessKeyId;

    /** access_key_secret */
    @ApiModelProperty(value = "access_key_secret")
    @NotNull(groups = {PostValid.class}, message = "accessKeySecret is null")
    @TableField("access_key_secret")
    private String accessKeySecret;

    @TableField("create_time")
    private Date createTime;

    @TableField("create_by")
    private Long createBy;


}
