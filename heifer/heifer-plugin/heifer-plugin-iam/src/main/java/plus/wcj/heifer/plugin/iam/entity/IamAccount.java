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
 * 账户表
 * </p>
 *
 * @author weichangjin (魏昌进)
 * @since 2022-04-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("iam_account")
@ApiModel(value = "IamAccount对象", description = "账户表")
public class IamAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @ApiModelProperty(value = "主键ID")
    @NotNull(groups = {PutValid.class}, message = "id is null")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 账户名 */
    @ApiModelProperty(value = "账户名")
    @NotNull(groups = {PostValid.class}, message = "username is null")
    @TableField("username")
    private String username;

    /** 手机号 */
    @ApiModelProperty(value = "手机号")
    @NotNull(groups = {PostValid.class}, message = "phone is null")
    @TableField("phone")
    private String phone;

    /** 邮箱 */
    @ApiModelProperty(value = "邮箱")
    @NotNull(groups = {PostValid.class}, message = "email is null")
    @TableField("email")
    private String email;

    /** 密码 */
    @ApiModelProperty(value = "密码")
    @NotNull(groups = {PostValid.class}, message = "password is null")
    @TableField("password")
    private String password;

    /** 昵称 */
    @ApiModelProperty(value = "昵称")
    @NotNull(groups = {PostValid.class}, message = "nickname is null")
    @TableField("nickname")
    private String nickname;

    /** 指示账户是启用还是禁用。禁用的账户无法通过身份验证。返回值：true如果账户已启用，false否则 */
    @ApiModelProperty(value = "指示账户是启用还是禁用。禁用的账户无法通过身份验证。返回值：true如果账户已启用，false否则")
    @NotNull(groups = {PostValid.class}, message = "enabled is null")
    @TableField("is_enabled")
    private Boolean enabled;

    @TableField("create_time")
    private Date createTime;

    @TableField("create_by")
    private Long createBy;

    @TableField("update_time")
    private Date updateTime;

    @TableField("update_by")
    private Long updateBy;


}
